/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package common.util.date;

import org.apache.log4j.Logger;

import javax.xml.bind.DatatypeConverter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class DateTimeUtility {

    private Logger log = Logger.getLogger(DateTimeUtility.class);

    public static final DateTimeUtility INSTANCE = new DateTimeUtility();

    public static final TimeZone NO_TIMEZONE = TimeZone.getTimeZone("Europe/Oslo");

    public Date retrieveLocalDateTime(String country) {
        return retrieveLocalDateTimeCalendar(country).getTime();
    }

    public String retrieveLocalDateTimeAsString(String country) {
        return DatatypeConverter.printDateTime(retrieveLocalDateTimeCalendar(country));
    }

    public String retrieveLocalDateAsString(String country) {
        return DatatypeConverter.printDate(retrieveLocalDateTimeCalendar(country));
    }

    public int retrieveLocalDateTimeYear(String country) {
        Calendar now = retrieveLocalDateTimeCalendar(country);
        return now.get(Calendar.YEAR);
    }

    public Calendar retrieveLocalDateTimeCalendar(String country) {
        TimeZone timeZone = findTimeZone(country);

        Calendar calendar = Calendar.getInstance(timeZone);

            log.debug("Local time in " + country + " is " +
                    format(calendar.getTime(), timeZone) + " in time zone " +
                    timeZone.getDisplayName(false, TimeZone.LONG, Locale.US) +
                    "(" + timeZone.getDisplayName(false, TimeZone.SHORT) +
                    ") " + (timeZone.inDaylightTime(calendar.getTime()) ?
                    "(summer time)" : "(winter time)"));
        return calendar;
    }

    public Boolean isInTimeFrame(Date date, String dayOfWeekClassifier,
                                 String startTime, String endTime, String country) {
        try {
            Calendar checkMe = Calendar.getInstance();
            checkMe.setTime(date);
            TimeZone timeZone = findTimeZone(country);
            int year = checkMe.get(Calendar.YEAR);
            int month = checkMe.get(Calendar.MONTH);
            int day = checkMe.get(Calendar.DAY_OF_MONTH);

            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

            Calendar start = Calendar.getInstance();
            start.setTime(format.parse(startTime));
            start.set(Calendar.YEAR, year);
            start.set(Calendar.MONTH, month);
            start.set(Calendar.DAY_OF_MONTH, day);
            start.setTimeZone(timeZone);

            Calendar end = Calendar.getInstance();
            end.setTime(format.parse(endTime));
            end.set(Calendar.YEAR, year);
            end.set(Calendar.MONTH, month);
            end.set(Calendar.DAY_OF_MONTH, day);
            end.setTimeZone(timeZone);

            boolean isPastMidnight = false;

            if (end.before(start)) {
                isPastMidnight = checkMe.before(end);

                if (isPastMidnight) {
                    start.add(Calendar.DATE, -1);
                } else {
                    end.add(Calendar.DATE, 1);
                }
            }

                log.debug("Checking if " + format(checkMe.getTime(), timeZone)
                        + " is a " + dayOfWeekClassifier
                        + (isPastMidnight ? " (past midnight)" : "")
                        + " between start time "
                        + format(start.getTime(), timeZone) + " and end time "
                        + format(end.getTime(), timeZone));

            int dayOfWeek = start.get(Calendar.DAY_OF_WEEK);

                log.debug("Time frame control: After start: "
                        + checkMe.after(start) + ", Before end: "
                        + checkMe.before(end) + ", Is day of week classifier: "
                        + isDayOfWeek(dayOfWeekClassifier, dayOfWeek, true));

            return checkMe.after(start) && checkMe.before(end)
                    && isDayOfWeek(dayOfWeekClassifier, dayOfWeek, false);
        } catch (ParseException e) {
            log.warn("Failure when detecting if date is in time frame", e);
            return Boolean.TRUE;
        }
    }

    public String convertDateTimeString(String dateTimeString,
                                        String inPattern, String outPattern)
            throws ParseException {
        DateFormat inFormat = new SimpleDateFormat(inPattern);
        DateFormat outFormat = new SimpleDateFormat(outPattern);
        return outFormat.format(inFormat.parse(dateTimeString));
    }

    public int calculateDiff(Calendar first, Calendar last, CalendarDiffUnit unit) {
        switch (unit) {
            case YEAR:
                int age = last.get(Calendar.YEAR) - first.get(Calendar.YEAR);

                if (age > 0 && (last.get(Calendar.MONTH) < first.get(Calendar.MONTH) ||
                        (last.get(Calendar.MONTH) == first.get(Calendar.MONTH) && last.get(Calendar.DAY_OF_MONTH) < first.get(Calendar.DAY_OF_MONTH)))) {
                    age--;
                }
                if (age < 0 && (last.get(Calendar.MONTH) > first.get(Calendar.MONTH) ||
                        (last.get(Calendar.MONTH) == first.get(Calendar.MONTH) && last.get(Calendar.DAY_OF_MONTH) > first.get(Calendar.DAY_OF_MONTH)))) {
                    age++;
                }
                return age;
            case MONTH:
                int years = last.get(Calendar.YEAR) - first.get(Calendar.YEAR);
                age = years * 12;

                age = age + last.get(Calendar.MONTH) - first.get(Calendar.MONTH);
                if (age > 0 && last.get(Calendar.DAY_OF_MONTH) < first.get(Calendar.DAY_OF_MONTH)) {
                    age--;
                } else if (age < 0 && last.get(Calendar.DAY_OF_MONTH) > first.get(Calendar.DAY_OF_MONTH)) {
                    age++;
                }
                return age;
            case WEEK:
                years = last.get(Calendar.YEAR) - first.get(Calendar.YEAR);
                age = years * 365;
                age = (age + last.get(Calendar.DAY_OF_YEAR) - first.get(Calendar.DAY_OF_YEAR)) / 7;
                return age;
            case DAY:
                years = last.get(Calendar.YEAR) - first.get(Calendar.YEAR);
                age = years * 365;
                age = age + last.get(Calendar.DAY_OF_YEAR) - first.get(Calendar.DAY_OF_YEAR);
                //we count the current day
                if (age >= 0)
                    age++;
                else if (age < 0)
                    age--;
                return age;
            case HOUR:
                // milliseconds in a hour = 60*60*1000
                age = Math.round((last.getTimeInMillis() - first.getTimeInMillis()) / 3600000);
                return age;
            case MINUTE:
                // milliseconds in a minute = 60*1000
                age = Math.round((last.getTimeInMillis() - first.getTimeInMillis()) / 60000);
                return age;
            case YEAR_FROM_AGE:
                age = last.get(Calendar.YEAR) - first.get(Calendar.YEAR);
                return age;
            default:
                return 0;
        }
    }

    public Date addToDate(Date date, int amount, DateUnit unit, String country) {
        Calendar input = retrieveLocalDateTimeCalendar(country);
        input.add(unit.getCalendarUnit(), amount);
        return input.getTime();
    }

    public enum CalendarDiffUnit {
        YEAR, MONTH, WEEK, DAY, HOUR, MINUTE, YEAR_FROM_AGE;
    }

    public enum DateUnit {
        YEAR(Calendar.YEAR), MONTH(Calendar.MONTH), WEEK(Calendar.WEEK_OF_YEAR), DAY(Calendar.DAY_OF_YEAR);

        private int calendarUnit;

        private DateUnit(int calendarUnit) {
            this.calendarUnit = calendarUnit;
        }

        public int getCalendarUnit() {
            return calendarUnit;
        }
    }

    private boolean isDayOfWeek(String classifier, int dayOfWeek,
                                boolean debugging) {
        if ("sunday".equals(classifier)) {
                log.debug("Checking day of week for sunday: " + dayOfWeek
                        + " == " + Calendar.SUNDAY);
            return dayOfWeek == Calendar.SUNDAY;
        } else if ("saturday".equals(classifier)) {
                log.debug("Checking day of week for saturday: " + dayOfWeek
                        + " == " + Calendar.SATURDAY);
            return dayOfWeek == Calendar.SATURDAY;
        } else {
                log.debug("Checking day of week for weekday: " + dayOfWeek
                        + " == [" + Calendar.MONDAY + ", " + Calendar.TUESDAY
                        + ", " + Calendar.WEDNESDAY + ", " + Calendar.THURSDAY
                        + ", " + Calendar.FRIDAY + "]");
            return dayOfWeek == Calendar.MONDAY
                    || dayOfWeek == Calendar.TUESDAY
                    || dayOfWeek == Calendar.WEDNESDAY
                    || dayOfWeek == Calendar.THURSDAY
                    || dayOfWeek == Calendar.FRIDAY;
        }
    }

    private String format(Date date, TimeZone timeZone) {
        SimpleDateFormat logFormat = new SimpleDateFormat(
                "E dd.MM.yyyy HH:mm.ss '(i tidssone:' Z ')'");
        logFormat.setTimeZone(timeZone);
        return logFormat.format(date);
    }
    
    public String formatDate(Date date) {
        if (date != null) {
                SimpleDateFormat logFormat = new SimpleDateFormat(
                        "yyyy-MM-dd");
                logFormat.setTimeZone(TimeZone.getTimeZone("Europe/Oslo"));
                return logFormat.format(date);
        }
        return null;
    }
    
    public String formatQuoteDate(Date date) {
        if (date != null) {
                SimpleDateFormat logFormat = new SimpleDateFormat(
                        "dd.MM.yyyy");
                logFormat.setTimeZone(TimeZone.getTimeZone("Europe/Oslo"));
                return logFormat.format(date);
        }
        return null;
    }

    private TimeZone findTimeZone(String country) {
        TimeZone timeZone = (country.equalsIgnoreCase("FI") == true) ? TimeZone
                .getTimeZone("GMT+2") : NO_TIMEZONE;
        return timeZone;
    }
    
    public XMLGregorianCalendar getXMLGregorianCalendar(String country) {
        Calendar localCalendar = this.retrieveLocalDateTimeCalendar(country);
        GregorianCalendar gc = (GregorianCalendar) localCalendar;
        XMLGregorianCalendar currServTime = null;
        try {
            currServTime = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
        } catch (Exception e) {
            currServTime=null;
             log.warn("Failure getting DateTimeUtility.XMLGregorianCalendar. Sending null in return", e);
             currServTime=null;
        }
        return currServTime;
    }
    
    public Date retrieveLocalDate(String country) {
		return DateTimeUtility.INSTANCE.retrieveLocalDateTime(country);
    }
    
    public Integer calculateDiffFromNowInDays(Date date) {
		return calculateDiffInDays(retrieveLocalDate("NO"), date);
    }

    public Integer calculateDiffFromNowInMinutes(Date date) {
            return calculateDiff(date, retrieveLocalDate("NO"), CalendarDiffUnit.MINUTE);
    }

    private Integer calculateDiff(Date first, Date last, CalendarDiffUnit timeUnit) {
            Calendar firstCalendar = DateTimeUtility.INSTANCE.retrieveLocalDateTimeCalendar("NO");
            firstCalendar.setTime(first);
            Calendar lastCalendar = DateTimeUtility.INSTANCE.retrieveLocalDateTimeCalendar("NO");
            lastCalendar.setTime(last);
            return DateTimeUtility.INSTANCE.calculateDiff(firstCalendar, lastCalendar, timeUnit);
    }

  
    public Integer calculateDiffInDays(Date first, Date last) {
            SimpleDateFormat logFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm.ss");
            TimeZone timeZone = TimeZone.getTimeZone("Europe/Oslo");
            logFormat.setTimeZone(timeZone);
            Calendar firstCalendar = DateTimeUtility.INSTANCE.retrieveLocalDateTimeCalendar("NO");
            firstCalendar.setTime(first);
            Calendar lastCalendar = Calendar.getInstance();
            lastCalendar.setTime(last);
            return DateTimeUtility.INSTANCE.calculateDiff(firstCalendar,lastCalendar, CalendarDiffUnit.DAY);
    }
}


