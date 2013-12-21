package work.idtheft;

import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.Seconds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestValidation {

    long startTimeNano = 0;
    long hitCounter = 0;
    List<DateTime> listOfHits = null;
    StringBuffer savedReport = new StringBuffer();
    RequestCapsel requestCapsel = null;
    long smallestAllowedDifferenceBetweenHits = 0;
    double differenceBetweenHitsAverage = 0.0;
    private Logger logger = LoggerFactory.getLogger(RequestValidation.class);

    public RequestValidation(RequestCapsel requestCapsel) {
        this.requestCapsel = requestCapsel;
        startTimeNano = System.nanoTime();
        listOfHits = requestCapsel.listOfHits;
        savedReport = new StringBuffer();
    }

    public long getSmallestAllowedDifferenceBetweenHits() {
        return smallestAllowedDifferenceBetweenHits;
    }

    public double getDifferenceBetweenHitsAverage() {
        return differenceBetweenHitsAverage;
    }

    /**
     * Kalkulerer om request er er valid
     *
     * Sjekker om man kan godkjenne denne requesten. Baserer seg paa historiske
     * data, men plukker alltid de siste NlastMeasureValues
     *
     * @param NlastMeasureValues Brukes her til ? plukke de n-siste m?linger
     *
     * @return
     */
    public boolean isValidateHits(int NlastMeasureValues) {
        smallestAllowedDifferenceBetweenHits = requestCapsel.getPeriodeSeconds() / requestCapsel.getAmountsHitsInPeriode();
        boolean validHits = true;
        DateTime lastTime = null;
        Seconds diff = null;
        int antallDiffMaalinger = 0;
        long akkumulertDiffISekunder = 0;

        //Subset, NlastMeasureValues last values
        List<DateTime> subsetOfListOfHits = LastMeasureValues(NlastMeasureValues, listOfHits);
        for (DateTime currentTime : subsetOfListOfHits) {
            if (currentTime == subsetOfListOfHits.get(0)) {
                lastTime = currentTime;
                continue;
            } else {
                antallDiffMaalinger++;
                diff = Seconds.secondsBetween(lastTime, currentTime);
                akkumulertDiffISekunder = akkumulertDiffISekunder + diff.getSeconds();
                lastTime = currentTime;
            }
        }
        if (antallDiffMaalinger > 0) {
            differenceBetweenHitsAverage = akkumulertDiffISekunder / antallDiffMaalinger;
            if (differenceBetweenHitsAverage < smallestAllowedDifferenceBetweenHits) {
                validHits = false;
            }
        } else {
            validHits = true;
        }
        //logger.info("Antall maalinger                :"+antallDiffMaalinger);  
        //logger.info("Minste periode mellom hits(sek) :"+smallestAllowedDifferenceBetweenHits);
        //logger.info("Gjennomsnitt kalkulert hits(sek) :"+differenceBetweenHitsAverage);  
        //logger.info("Resultat av validering           :"+validHits);  
        return validHits;
    }

    private static List<DateTime> LastMeasureValues(int lastValues, List<DateTime> list) {
        List<DateTime> nlsubset = null;
        int sizeOfList = list.size();
        int diff = sizeOfList - lastValues;
        if (diff <= 0) {
            nlsubset = list;
        } else {
            nlsubset = list.subList(diff, sizeOfList);
        }
        return nlsubset;
    }
}
