package com.vietinterview.getbee.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by hienvv7 on 5/26/2017.
 */

public class DateUtil {
    /**
     * The date format in iso.
     */

    private static DateFormat dateFormat;

    public synchronized static Date convertToGMTDate(String Date) {
        Date converted_date = null;
        try {
            DateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            utcFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            Date date = utcFormat.parse(Date);
            converted_date = date;
        } catch (Exception e) {
        }
        try {
            DateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            utcFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            Date date = utcFormat.parse(Date);
            converted_date = date;
        } catch (Exception e) {
        }
        return converted_date;
    }

    public synchronized static String convertToGMTTimeZone(String Date) {
        dateFormat = DateFormat.getDateInstance(DateFormat.LONG, Locale.getDefault());
        String converted_date = null;
        String converted_date2 = null;
        try {
            DateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+0000");
            utcFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            Date date = utcFormat.parse(Date);
            converted_date = dateFormat.format(date);
        } catch (Exception e) {
        }
        try {
            DateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS+0000");
            utcFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            Date date = utcFormat.parse(Date);
            converted_date2 = dateFormat.format(date);
        } catch (Exception e) {
        }
        if (converted_date != null) return converted_date;
        return converted_date2;
    }

    public synchronized static String convertToMyFormat(String Date) {
        String converted_date = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.US);
            Date date = sdf.parse(Date);

            SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
            converted_date = sdf2.format(date.getTime());
        } catch (Exception e) {
        }
        return converted_date;
    }

    public synchronized static String convertToMyFormatFull(String Date) {
        String converted_date = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.US);
            Date date = sdf.parse(Date);

            SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
            converted_date = sdf2.format(date.getTime());
        } catch (Exception e) {
        }
        return converted_date;
    }
    public synchronized static String convertToMyFormatVacant(String Date) {
        String converted_date = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM", Locale.US);
            Date date = sdf.parse(Date);

            SimpleDateFormat sdf2 = new SimpleDateFormat("MM/yyyy");
            converted_date = sdf2.format(date.getTime());
        } catch (Exception e) {
        }
        return converted_date;
    }
    public static final String FORMAT_DATE_ISO = "yyyy-MM-dd HH:mm:ss";

    /**
     * Takes in an ISO date string of the following format:
     * yyyy-mm-ddThh:mm:ss.ms+HoMo
     *
     * @param isoDateString the iso date string
     * @return the date
     * @throws Exception the exception
     */
    public synchronized static Date fromISODateString(String isoDateString)
            throws Exception {
        DateFormat f = new SimpleDateFormat(FORMAT_DATE_ISO);
        return f.parse(isoDateString);
    }

    /**
     * Render date
     *
     * @param date   the date obj
     * @param format - if not specified, will use FORMAT_DATE_ISO
     * @param tz     - tz to set to, if not specified uses local timezone
     * @return the iso-formatted date string
     */
    public synchronized static String toISOString(Date date, String format, TimeZone tz) {
        if (format == null) format = FORMAT_DATE_ISO;
        if (tz == null) tz = TimeZone.getDefault();
        DateFormat f = new SimpleDateFormat(format);
        f.setTimeZone(tz);
        return f.format(date);
    }

    public synchronized static String toISOString(Date date) {
        return toISOString(date, FORMAT_DATE_ISO, TimeZone.getTimeZone("UTC"));
    }
}
