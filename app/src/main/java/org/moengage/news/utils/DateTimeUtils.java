package org.moengage.news.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Shahbaz Hashmi on 2020-03-21.
 */
public class DateTimeUtils {

    private static final String API_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    private static final String DESIRED_FORMAT = "E, d MMM yyyy h:mm a";


    public static long getTimestamp(String dateTime) {
        SimpleDateFormat inputFormat = new SimpleDateFormat(API_FORMAT, Locale.ENGLISH);
        Date date = null;
        try {
            date = inputFormat.parse(dateTime);
        } catch (Exception e) {
            return 0;
        }
        return date.getTime();
    }


    public static String getFormattedDateTime(long timestamp) {
        SimpleDateFormat outputFormat = new SimpleDateFormat(DESIRED_FORMAT, Locale.ENGLISH);

        Date date = null;
        String str = null;

        try {
            date = new Date(timestamp);
            str = outputFormat.format(date);
        } catch (Exception e) {
            str = "";
            e.printStackTrace();
        }
        return str;
    }

}
