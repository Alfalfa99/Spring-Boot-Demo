package com.alfalfa.demo.utils;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author 15423
 */
public class DateTimeTransfer {
    public static String getNowTimeStamp(){
        long time = System.currentTimeMillis();
        time/=1000;
        return String.valueOf(time);
    }

    public static String getFormatTime(){
        String format = "yyyy-MM-dd HH:mm:ss";
        String date = new SimpleDateFormat(format, Locale.CHINA).format(new Date());
        return date;
    }
}
