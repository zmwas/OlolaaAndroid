package com.ololaa.ololaa.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static Date getDateFromString(String date, String dateFormat) {
        if (date != null && dateFormat != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
                return sdf.parse(date);
            } catch (Exception e) {
            }
        }
        return null;
    }

    public static String formatDate(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
    public static String formatDate(Date date, String format) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }


}
