package io.github.louistsaitszho.erg2.unit;

import java.util.GregorianCalendar;

/**
 * Created by Louis on 5/26/2015.
 */
public class Record {
    private GregorianCalendar startTime;    //start date time
    private double distance; //in meter
    private int rating;
    private int duration;   //in millisecond



    //Getter and Setter

    public Record() {
        startTime = new GregorianCalendar();    //current date and time
        distance = 7000;                        //7000m
        rating = 18;                            //rating 18
        duration = 1800000;                     //30 minutes
    }

    public Record(GregorianCalendar startTime, int duration, int rating, double distance) {
        this.startTime = startTime;
        this.duration = duration;
        this.rating = rating;
        this.distance = distance;
    }

    public GregorianCalendar getStartTime() {
        return startTime;
    }

    public void setStartTime(GregorianCalendar startTime) {
        this.startTime = startTime;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
