package com.deeprooted.ordermanager.utils;

public class Utility {
    public static Long convertTimeInMillis(String time){
        String hhmm[] = time.split(":");
        Long timeInMillis = (long)(Integer.parseInt(hhmm[0]) * 60 * 60 + Integer.parseInt(hhmm[1])*60)*1000;
        return timeInMillis; //System.currentTimeMillis();
    }
}
