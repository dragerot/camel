package work.idtheft;

import common.util.date.DateTimeUtility;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import org.joda.time.DateTime;

public class RequestCapsel implements java.io.Serializable {
    long periodeSeconds = 0;
    int amountsHitsInPeriode = 0;
    List<DateTime> listOfHits = null; 
    List<String> datoFormattedHits =null; 
    boolean karantene = false;
    
    /**
     * Constructor
     */
    public RequestCapsel(long periodeSeconds, int amountsHitsInPeriode) {
        listOfHits = new ArrayList<DateTime>();
        datoFormattedHits = new ArrayList<String>();
        this.setPeriodeSeconds(periodeSeconds);
        this.setAmountsHitsInPeriode(amountsHitsInPeriode);
    }

    public void setAmountsHitsInPeriode(int amountsHitsInPeriode) {
        this.amountsHitsInPeriode = amountsHitsInPeriode;
    }

    public int getAmountsHitsInPeriode() {
        return amountsHitsInPeriode;
    }

    public boolean isKarantene() {
        return karantene;
    }

    public void setKarantene(boolean value) {
        karantene = value;
    }

    public void NewHit() {
        listOfHits.add(DateTime.now());
        datoFormattedHits.add(getCurrentDate());
    }

    public List<DateTime> getList() {
        return listOfHits;
    }

    public void setList(List<DateTime> list) {
        listOfHits = list;
    }

    public void setPeriodeSeconds(long periodeSeconds) {
        this.periodeSeconds = periodeSeconds;
    }

    public long getPeriodeSeconds() {
        return periodeSeconds;
    }
    
    public List<String> getHistory() {
        return datoFormattedHits;
    }
    
    private static String getCurrentDate()
    {         
       DateTimeUtility dateTimeUtility=DateTimeUtility.INSTANCE;
       Date dato=dateTimeUtility.retrieveLocalDateTime("NO"); 
       SimpleDateFormat format = new SimpleDateFormat("dd:MM:yyyy:HH:mm:ss:SSS");
       format.setTimeZone(TimeZone.getTimeZone("Europe/Oslo"));
       return format.format(dato);
    }
    
    private static long ConvertFromMillSecondsToNano(long milliseconds) {
        return TimeUnit.MILLISECONDS.toNanos(milliseconds);
    }
    
    private static long ConvertFromNanoToMillSeconds(long nanoseconds) {
        return TimeUnit.NANOSECONDS.toMillis(nanoseconds);
    }
    
    private static long ConvertFromNanoToMinutes(long nanoseconds) {
        return TimeUnit.NANOSECONDS.toMinutes(nanoseconds);
    }
}
