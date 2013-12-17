package work.idtheft;

import java.util.List;
import java.util.concurrent.*; 


public class RequestValidation {
	long startTimeNano 	= 0;
	long hitCounter		= 0; 
	List<Long> listOfHits=null; 
	StringBuffer savedReport=new StringBuffer();
	RequestCapsel requestCapsel=null;
        long smallestAllowedDifferenceBetweenHits=0;
        double differenceBetweenHitsAverage=0.0;
        
     	public RequestValidation(RequestCapsel requestCapsel)
	{
		this.requestCapsel=requestCapsel;
		startTimeNano= System.nanoTime(); 
		listOfHits=requestCapsel.listOfHits;
		savedReport=new StringBuffer();
	}
        
        public long getSmallestAllowedDifferenceBetweenHits() {return smallestAllowedDifferenceBetweenHits;}
        public double getDifferenceBetweenHitsAverage() {return differenceBetweenHitsAverage;}
	
	/**
	 * Kalkulerer om request er er  valid
	 * 
	 * Sjekker om man kan godkjenne denne requesten.
	 * Baserer seg på historiske data, men plukker alltid de siste 
	 * NlastMeasureValues
	 * 
	 * @param NlastMeasureValues Brukes her til å plukke de n-siste målinger 
	 * 
	 * @return
	 */
	public boolean isValidateHits(int NlastMeasureValues) 
	{
	    smallestAllowedDifferenceBetweenHits =requestCapsel.getPeriodeSeconds()/requestCapsel.getAmountsHitsInPeriode();
	    boolean valid = true;
	    long _lastTime = 0;
	    long diff=0;
	    int antallDiffMaalinger=0;
	    long akkumulertDiffISekunder=0;
	  	    
	    //Subset, NlastMeasureValues last values
		List<Long> subsetOfListOfHits=LastMeasureValues(NlastMeasureValues,listOfHits);
		savedReport.append(GetTableHeaderPrintFormat());
		for (Long tid : subsetOfListOfHits) 
		{
			if (tid == subsetOfListOfHits.get(0)) 
			{
				_lastTime = tid.longValue();	
				continue;
			} 
			else 
			{   
				antallDiffMaalinger++;
				diff=ConvertFromNanoToSeconds(tid.longValue() - _lastTime);
				akkumulertDiffISekunder=akkumulertDiffISekunder+diff; 
				_lastTime = tid.longValue();
				savedReport.append(GetDetailPrintFormat(antallDiffMaalinger,diff,akkumulertDiffISekunder));
			}
		}
	      if(antallDiffMaalinger>0) 
		{
			  differenceBetweenHitsAverage=akkumulertDiffISekunder/antallDiffMaalinger;
			  if(differenceBetweenHitsAverage<smallestAllowedDifferenceBetweenHits) valid=false;
		}else
		{
			valid=true;
		}
	    savedReport.append("Minstre periode mellom hits(sek) :"+smallestAllowedDifferenceBetweenHits+"\n");
	    savedReport.append("Gjennomsnitt kalkulert hits(sek) :"+differenceBetweenHitsAverage+" på "+antallDiffMaalinger+" målinger"+"\n");
	    savedReport.append("Resultat av validering           :"+valid+"\n");
	    return valid;
	}
	
	/**
	 * Pick the last requestCapsel.getAmountsHitsInPeriode() values 
	 * to calculate the average
	 *  
	 * @param lastValues
	 * @param list
	 * @return
	 */
	public static List<Long> LastMeasureValues(int lastValues,List<Long> list)
	{
		List<Long> nlsubset=null;
		int sizeOfList=list.size();
		int diff=sizeOfList-lastValues;
		if(diff<=0) nlsubset=list;  
		else nlsubset=list.subList(diff, sizeOfList);
		return nlsubset; 
	}
	
	
	public static long ConvertFromNanoToSeconds(long nano)
	{
		return TimeUnit.NANOSECONDS.toSeconds(nano);
	}
	public static long ConvertFromSecondsToNano(long seconds)
	{
	  
	  return TimeUnit.SECONDS.toNanos(seconds);
	}
	public static long ConvertFromMillSecondsToNano(long milliseconds)
	{
	  return TimeUnit.MILLISECONDS.toNanos(milliseconds);
	}
        
        public String ValidateHitsReport()
	{
		return savedReport.toString();
	}
	
	private String GetTableHeaderPrintFormat()
	{
		return "Antall målinger/Diff tid/Akkulert tid\n";
	}
	
	private String GetDetailPrintFormat(int maaling,long diff, long akkDiff)
	{
		return String.format("%d    %d    %d \n",maaling, diff,akkDiff).toString() ;
	}
	
}
