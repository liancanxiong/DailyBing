package com.brilliantbear.dailybing.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Bear on 2016-6-16.
 */
public class DateUtils {

    public static String changeDateFormat(String date, String from, String to) {
        SimpleDateFormat fromFormat = new SimpleDateFormat(from);
        SimpleDateFormat toFormat = new SimpleDateFormat(to);
        String result = null;
        try {
            Date newDate = fromFormat.parse(date);
            result = toFormat.format(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
}
