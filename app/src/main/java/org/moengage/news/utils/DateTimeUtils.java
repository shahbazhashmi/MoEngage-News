package org.moengage.news.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Shahbaz Hashmi on 2020-03-21.
 */
public class DateTimeUtils {

    private static final String API_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final String DESIRED_FORMAT = "E, d MMM yyyy h:mm a";


    public static String getFormattedDateTime(String dateTime) {
        SimpleDateFormat inputFormat = new SimpleDateFormat(API_FORMAT, Locale.ENGLISH);
        SimpleDateFormat outputFormat = new SimpleDateFormat(DESIRED_FORMAT, Locale.ENGLISH);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(dateTime.replace("Z", ""));
            str = outputFormat.format(date);
        } catch (ParseException e) {
            str = dateTime;
            e.printStackTrace();
        }
        return str;
    }

}
