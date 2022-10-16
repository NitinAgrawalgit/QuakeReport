package com.example.quakereport;

import android.widget.TextView;

public final class DateUtils {
    public static TextView monthTV;
    public static String stringUrl = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2022-10-01&endtime=2022-10-28&minmagnitude=2&limit=100";

    public static void setStartDate(String date){
        String parts[] = stringUrl.split("&", 3);

        stringUrl = parts[0] + "&starttime=";
        stringUrl += date;
        stringUrl += "&" + parts[2];
    }

    public static void setEndDate(String date){
        String parts[] = stringUrl.split("&", 4);
        stringUrl = parts[0] + "&" + parts[1] + "&endtime=";
        stringUrl += date;
        stringUrl += "&" + parts[3];
    }

    public static String getMonthName(int n){
        String s = null;

        switch(n){
            case 1:
                s = "january";
                break;
            case 2:
                s = "february";
                break;
            case 3:
                s = "march";
                break;
            case 4:
                s = "april";
                break;
            case 5:
                s = "may";
                break;
            case 6:
                s = "june";
                break;
            case 7:
                s = "july";
                break;
            case 8:
                s = "august";
                break;
            case 9:
                s = "september";
                break;
            case 10:
                s = "october";
                break;
            case 11:
                s = "november";
                break;
            case 12:
                s = "december";
                break;
        }

        return s;
    }

    public static int incrementMonth(){
        int n;

        String parts[] = stringUrl.split("starttime=2022-");
        n = Integer.parseInt(parts[1].substring(0, 2));
        if(n < 10){
            n++;
        }
        stringUrl = parts[0] + "starttime=2022-";
        if(n < 10){
            stringUrl += "0";
        }
        stringUrl += String.valueOf(n);
        stringUrl += parts[1].substring(2, parts[1].length());

        String parts2[] = stringUrl.split("endtime=2022-");
        n = Integer.parseInt(parts2[1].substring(0, 2));
        if(n < 10){
            n++;
        }
        stringUrl = parts2[0] + "endtime=2022-";
        if(n < 10){
            stringUrl += "0";
        }
        stringUrl += String.valueOf(n);
        stringUrl += parts2[1].substring(2, parts2[1].length());

        return n;
    }

    public static int decrementMonth(){
        int n;

        String parts[] = stringUrl.split("starttime=2022-");
        n = Integer.parseInt(parts[1].substring(0, 2));
        if(n > 1){
            n--;
        }
        stringUrl = parts[0] + "starttime=2022-";
        if(n < 10){
            stringUrl += "0";
        }
        stringUrl += String.valueOf(n);
        stringUrl += parts[1].substring(2, parts[1].length());

        String parts2[] = stringUrl.split("endtime=2022-");
        n = Integer.parseInt(parts2[1].substring(0, 2));
        if(n > 1){
            n--;
        }
        stringUrl = parts2[0] + "endtime=2022-";
        if(n < 10){
            stringUrl += "0";
        }
        stringUrl += String.valueOf(n);
        stringUrl += parts2[1].substring(2, parts2[1].length());

        return n;
    }
}
