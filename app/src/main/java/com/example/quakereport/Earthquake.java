package com.example.quakereport;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Earthquake {
    private double mMagnitude;
    private String mLocation;
    private long mTime;
    private String mUrl;

    public Earthquake(double Magnitude, String Location, long Time, String Url) {
        this.mMagnitude = Magnitude;
        this.mLocation = Location;
        this.mTime = Time;
        this.mUrl = Url;
    }

    public String getmMagnitude() {
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        return decimalFormat.format(mMagnitude);
    }

    public String getmLocation() {
        return mLocation;
    }

    public String getmTime() {
        return String.valueOf(mTime);
    }

    public String getmUrl() {
        return mUrl;
    }

    public String getFormattedDate(){
        Date dateObject = new Date(mTime);
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy", Locale.US);
        return dateFormat.format(dateObject);
    }

    public String getFormattedTime(){
        Date dateObject = new Date(mTime);
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a", Locale.US);
        return timeFormat.format(dateObject);
    }
}
