package io.github.louistsaitszho.erg2.unit;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Each records stores the total distance and duration only.
 */
public class Record {
    public static final int METER_PER_SECOND = 1;
    public static final int KM_PER_HOUR = 2;
    public static final int SECOND_TO_MILLISECOND = 1000;
    public static final int MINUTE_TO_SECOND = 60;
    public static final int HOUR_TO_SECOND = 3600;
    public static final int INVALID_INT = -1;
    public static final GregorianCalendar INVALED_GC = new GregorianCalendar(-1, -1, -1);
    private static final double DEFAULT_DISTANCE = 7000;
    private static final int DEFAULT_RATING = 18;
    private static final int DEFAULT_DURATION = 1800000;
    public GregorianCalendar startTime;    //start date time
    private double distance; //in meter
    private int rating;
    private int duration;   //in millisecond
    // Maybe attachment?

    /**
     * This is the default constructor of Record class. It create an instance of Record of 7000m
     * in 30 minutes with rating 18 and start time of current time
     */
    public Record() {
        startTime = new GregorianCalendar();    //current date and time
        distance = DEFAULT_DISTANCE;            //7000m
        rating = DEFAULT_RATING;                //rating 18
        duration = DEFAULT_DURATION;            //30 minutes
    }

    /**
     * This constructor create an instance of Record according to the 4 inputs
     * @param startTime is the start time of this instance of record
     * @param duration  is the total duration of this record in millisecond
     * @param rating is the overall average rating of this record
     * @param distance is the total distance of this record in meter
     */
    public Record(GregorianCalendar startTime, int duration, int rating, double distance) {
        setStartTime(startTime);
        setDuration(duration);
        setRating(rating);
        setDistance(distance);
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

    public double getSpeed(int unit) {
        double speed = 0;
        switch (unit) {
            case METER_PER_SECOND:
                speed = (distance / duration) / SECOND_TO_MILLISECOND;
                break;
            case KM_PER_HOUR:
                speed = ((distance / 1000) / HOUR_TO_SECOND) / SECOND_TO_MILLISECOND ;
                break;
            default:
                speed = (distance / duration) / SECOND_TO_MILLISECOND;  //return m/s
        }
        return speed;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("Distance: " + getDistance() + "\n");
        output.append("Duration: " + getDuration() + "\n");
        output.append("rating: " + rating + "\n");
        output.append("Start Date: " + startTime.get(Calendar.YEAR) + "/" + startTime.get(Calendar.MONTH) + "/" + startTime.get(Calendar.DAY_OF_MONTH) + "\n");
        output.append("Start Time: " + startTime.get(Calendar.HOUR) + ":" + startTime.get(Calendar.MINUTE));
        return output.toString();
    }

}
