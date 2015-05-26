package io.github.louistsaitszho.erg2.unit;

import java.util.GregorianCalendar;

/**
 * Created by Louis on 5/26/2015.
 */
public class Record {
    private static final double DEFAULT_DISTANCE = 7000;
    private static final int DEFAULT_RATING = 18;
    private static final int DEFAULT_DURATION = 1800000;
    private GregorianCalendar startTime;    //start date time
    private double distance; //in meter
    private int rating;
    private int duration;   //in millisecond

    /**
     * This is the default constructor of Record class. It create an instance of Record of 7000m
     * in 30 minutes with rating 18 and start time of current time
     */
    public Record() {
        startTime = new GregorianCalendar();    //current date and time
        distance = DEFAULT_DISTANCE;                        //7000m
        rating = DEFAULT_RATING;                            //rating 18
        duration = DEFAULT_DURATION;                         //30 minutes
    }

    /**
     * This constructor create an instance of Record according to the 4 inputs
     * @param startTime is the start time of this instance of record
     * @param duration  is the total duration of this record in millisecond
     * @param rating is the overall average rating of this record
     * @param distance is the total distance of this record in meter
     */
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
