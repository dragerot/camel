package work.handler;

import work.domain.message.Response;
import work.domain.message.StatusType;
import work.idtheft.RequestCapsel;
import work.idtheft.RequestValidation;
import work.domain.message.Request;

import common.fault.BusinessFault;
import common.fault.FaultUtility;
import common.info.SystemInfo;

import java.util.List;
import javax.annotation.Resource;
import net.sf.ehcache.Element;
import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author TG3
 */
public class IdTheftCache {

    @Resource(name = "cacheManager")
    private CacheManager cacheManager;
    private @Value("${idtheft.CACHE_NAME}")
    String CACHE_NAME;
    private @Value("${idtheft.PERIODE_SECONDS}")
    long PERIODE_SECONDS;
    private @Value("${idtheft.AMOUNTSHITSINPERIODE}")
    int AMOUNTSHITSINPERIODE;
    private @Value("${idtheft.NLASTHITS}")
    int NLASTHITS;
    private @Value("${idtheft.LOCHOUT_TIME_SECONDS}")
    int LOCHOUT_TIME_SECONDS;
    private @Value("${idtheft.ACTIVATED}")
    String ACTIVATED;  //true or false/null
    static final String BUSINESSFAULT_ID_4603 = "4603";
    static final String BUSINESSFAULT_TECHNICAL_MESSAGE_4603 = "Too many identification attempts or already locked out due to quarantine time: key=";
    static final String BUSINESSFAULT_USER_FRIENDLY_MESSAGE_4603 = "Mulig Id Tyveri på oppgitte SSN/etternavn: key=";
    static final String SYSTEMFAULT_ID_4603 = "1659";
    static final String SYSTEMFAULT_TECHNICAL_MESSAGE_4603 = "Unexpected fault in IdentificationTheftPrevention";
    static final String SYSTEMFAULT_USER_FRIENDLY_MESSAGE_4603 = "System feil under Id Tyveri sjekk: key=";
    private Logger logger = LoggerFactory.getLogger(IdTheftCache.class);

    protected String getKey(Request request) {
        StringBuilder key = new StringBuilder();
        String ssn6 = request.getSsn().trim().substring(0, 6);
        key.append(ssn6).append(request.getFamilyName());
        return key.toString();
    }
    
    public void IdentificationIdTheftValidation(Exchange exchange) {
        Request request = exchange.getIn().getBody(Request.class);
        StatusType status = StatusType.Ok;
        Response response = new Response();
        if (ACTIVATED != null && ACTIVATED.trim().toUpperCase().equals("TRUE")) {
            logger.info("IdentificationIdTheftValidation IN");
            logger.info("CACHE_NAME:         :" + CACHE_NAME);
            logger.info("PERIODE_SECONDS:     :" + PERIODE_SECONDS);
            logger.info("AMOUNTSHITSINPERIODE:" + AMOUNTSHITSINPERIODE);
            logger.info("NLASTHITS           :" + NLASTHITS);
            logger.info("LOCHOUT_TIME_SECONDS        :" + LOCHOUT_TIME_SECONDS);
            status = ValidateIdTheft(request);
        } else {
            status=StatusType.Id_Theft_Disabled;
            logger.info("ID THEFT not activated. Set the environment variable to 'ACTIVATED = true'");
        }
        response.setStatus(status);
        exchange.getIn().setBody(response);
    }

    protected StatusType ValidateIdTheft(Request request) {
        StatusType status = StatusType.Ok;
        String key = getKey(request);
        logger.info("ValidateIdTheft Cache key :" + key);
        RequestValidation requestValidation = null;
        RequestCapsel requestCapsel = lookupRequestCapsel(key);

        if (requestCapsel == null) {
            //New id theft periode
            logger.info("ValidateIdTheft new requestCapsel: Key=" + key);
            requestCapsel = new RequestCapsel(PERIODE_SECONDS, AMOUNTSHITSINPERIODE);
            requestCapsel.NewHit();
            addToCache(key, requestCapsel);
        } else {
            //Sjekk RequestCapsel
            logger.info("ValidateIdTheft Update requestCapsel: Key=" + key);
            if (!requestCapsel.isKarantene()) {
                logger.info("ValidateIdTheft requestCapsel ikke i karentene: Key=" + key);
                requestCapsel.NewHit();
                logger.info("ValidateIdTheft New Hit registrert. Sjekk karantene : Key=" + key);
                requestValidation = new RequestValidation(requestCapsel);
                boolean validation = requestValidation.isValidateHits(NLASTHITS);
                logger.info("ValidateIdTheft SmallestAllowedDifferenceBetweenHits=" + requestValidation.getSmallestAllowedDifferenceBetweenHits() + " Key=" + key);
                logger.info("ValidateIdTheft DifferenceBetweenHitsAverage=" + requestValidation.getDifferenceBetweenHitsAverage() + " Key=" + key);
                if (!validation) {
                    //Ikke godkjent. Send i karantene
                    logger.info("ValidateIdTheft requestCapsel ikke godkjent. Send i karantene : Key=" + key);
                    requestCapsel.setKarantene(true);
                    UpdateCacheKarantene(key, requestCapsel);
                } else {
                    //Lagre RequestCapsel oppdatert i cache
                    logger.info("ValidateIdTheft Uppdater requestCapsel : Key=" + key);
                    updateCache(key, requestCapsel);
                }
            }
            if (requestCapsel.isKarantene()) {//Meld fra at man er sendt i karantene for første eller gjentatte spørringer
                logger.info("ValidateIdTheft Kaster BusinessFault pga potensiell Id tyver på nøkkel =" + key);
                List<String> history = requestCapsel.getHistory();
                requestValidation = new RequestValidation(requestCapsel);
                LogRequestCapselFrequence(key, requestValidation.getSmallestAllowedDifferenceBetweenHits(), requestValidation.getDifferenceBetweenHitsAverage());
                logRequestCapselHistory(key, requestCapsel);
                throw IDTheftBusinessFault(key, requestCapsel, request.getSystemInfo());
            }
        }
        return status;
    }

    private void LogRequestCapselFrequence(String key, long smallestAllowedDifferenceBetweenHits, double differenceBetweenHitsAverage) {
        if (smallestAllowedDifferenceBetweenHits > 0) {
            logger.info("ValidateIdTheft Smallest Allowed hits in periode=" + smallestAllowedDifferenceBetweenHits + " Key=" + key);
        }
        if (differenceBetweenHitsAverage > 0) {
            logger.info("ValidateIdTheft Average measured hits in periode=" + differenceBetweenHitsAverage + " Key=" + key);
        }
    }

    private void logRequestCapselHistory(String key, RequestCapsel requestCapsel) {
        List<String> history = requestCapsel.getHistory();
        for (String hit : history) {
            logger.info("ValidateIdTheft RequestCapsel hits :" + key + "  " + hit);
        }
    }

    protected BusinessFault IDTheftBusinessFault(String key, RequestCapsel requestCapsel, SystemInfo systemInfo) {

        FaultUtility faultUtility = new FaultUtility();
        BusinessFault businessFault = faultUtility.getBusinessFaultException(BUSINESSFAULT_ID_4603, BUSINESSFAULT_ID_4603, BUSINESSFAULT_TECHNICAL_MESSAGE_4603 + key, BUSINESSFAULT_USER_FRIENDLY_MESSAGE_4603 + key, systemInfo);
        return businessFault;
    }

    private Cache getCache() {
        return cacheManager.getCache(CACHE_NAME);
    }

    protected void addToCache(String key, RequestCapsel requestCapsel) {
        Cache cache = getCache();
        cache.put(key, requestCapsel);
    }

    protected void updateCache(String key, RequestCapsel requestCapsel) {
        if (requestCapsel != null) {
            addToCache(key, requestCapsel);
        }
    }

    protected void UpdateCacheKarantene(String key, RequestCapsel requestCapsel) {
        Cache cache = getCache();
        net.sf.ehcache.Cache _cache = (net.sf.ehcache.Cache) cache.getNativeCache();
        Element element = new Element(key, requestCapsel, LOCHOUT_TIME_SECONDS, 0);
        _cache.put(element);
    }

    protected RequestCapsel lookupRequestCapsel(String key) {
        Cache cache = getCache();
        Cache.ValueWrapper valueWrapper = cache.get(key);
        if (valueWrapper != null) {
            return (RequestCapsel) valueWrapper.get();
        } else {
            return null;
        }
    }
}
