package io.github.louistsaitszho.erg2.unit;

import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;

import io.github.louistsaitszho.erg2.R;

/**
 * Each records stores the total distance and duration only.
 */
public class Record {
    public static final int METER_PER_SECOND = 1;
    public static final int KM_PER_HOUR = 2;
    public static final int SECOND_TO_MILLISECOND = 1000;
    public static final int MINUTE_TO_SECOND = 60;
    public static final int HOUR_TO_SECOND = 3600;
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
        startTime = new GregorianCalendar();     //current time
//        startTime.setTime(new Date());
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

    /**
     * This constructor create an instance of Record according to 4 inputs, but this time the
     * startTime is in string format, which is how it have been stored in database
     *
     * @param startTime is the start time of this instance of record in String, which will be
     *                  converted back to GregorianCalendar object first
     * @param duration  is the total duration of this record in millisecond
     * @param rating    is the overall average rating of this record
     * @param distance  is the total distance of this record in meter
     */
    public Record(String startTime, int duration, int rating, double distance) {
        this();
        setStartTime(StringToGC(startTime));
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
        double speed;
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
        return "Distance: " + getDistance() + "\n" + "Duration: " + getDuration() + "\n" + "rating: " + rating + "\n" + "Start Date: " + startTime.get(Calendar.YEAR) + "/" + (1 + startTime.get(Calendar.MONTH)) + "/" + startTime.get(Calendar.DAY_OF_MONTH) + "\n" + "Start Time: " + startTime.get(Calendar.HOUR_OF_DAY) + ":" + startTime.get(Calendar.MINUTE);
    }

    /**
     * This method converts a GregorianCalendar instance to a string, which is how to store
     * startTime in database
     * @param gc is a GregorianCalendar object (i.e. startTime)
     * @return the string to be store, with each value separated by a "/"
     */
    private String GCToString(GregorianCalendar gc, int style) {
        StringBuilder output = new StringBuilder();
        switch (style) {
            case R.integer.START_DATETIME_STRING_STORAGE: //For storage
                output.append(gc.get(Calendar.YEAR));
                output.append("/");
                output.append(gc.get(Calendar.MONTH));
                output.append("/");
                output.append(gc.get(Calendar.DAY_OF_MONTH));
                output.append("/");
                output.append(gc.get(Calendar.HOUR_OF_DAY));
                output.append("/");
                output.append(gc.get(Calendar.MINUTE));
                break;
            case R.integer.START_DATETIME_STRING_EXACT: //For display(the exact time)
                output.append(gc.get(Calendar.YEAR));
                output.append("/");
                output.append(gc.get(Calendar.MONTH));
                output.append("/");
                output.append(gc.get(Calendar.DAY_OF_MONTH));
                output.append(" ");
                output.append(gc.get(Calendar.HOUR_OF_DAY));
                output.append(":");
                output.append(gc.get(Calendar.MINUTE));
                break;
            case R.integer.START_DATETIME_STRING_DIFFERENCE: //For display(how old)
                //TODO calculate how long ago
                //get current date time
                //range: today >> ? day(s) >> ? week(s) >> ? month(s) >> ? year(s)
                break;
        }
        return output.toString();
    }

    /**
     * This method reverse the process of GCToString, converts the startTime in string form back to
     * a GregorianCalendar instance
     * @param s is a string of startTime
     * @return a GregorianCalendar object
     */
    private GregorianCalendar StringToGC(String s) {
        ArrayList<Integer> values = new ArrayList<>();
        Log.d("StringToGC", s);
        try {
            for (String value : s.split("/", 5)) {
                Log.d("Value", value);
                values.add(Integer.parseInt(value));
            }
            return new GregorianCalendar(values.get(0), values.get(1), values.get(2), values.get(3), values.get(4));
        } catch (NumberFormatException e) {
            //TODO I don't know, since this should not happen
            return new GregorianCalendar();
        }
    }

    /**
     * This method return the startTime in string form
     * @return startTime in string form
     */
    public String startTimeToString(int style) {
        return GCToString(getStartTime(), style);
    }

    public int per500() {
        double a = getDistance() / 500;
        return (int) (getDuration() / a);
    }

    class StartTimeCompare implements Comparator<Record> {
        @Override
        public int compare(Record lhs, Record rhs) {
            GregorianCalendar gc1 = lhs.getStartTime();
            GregorianCalendar gc2 = rhs.getStartTime();
            return gc1.compareTo(gc2);
        }
    }

    class DistanceCompare implements Comparator<Record> {
        @Override
        public int compare(Record lhs, Record rhs) {
            Double d1 = lhs.getDistance();
            Double d2 = rhs.getDistance();
            return d1.compareTo(d2);
        }
    }

    class DurationCompare implements Comparator<Record> {
        @Override
        public int compare(Record lhs, Record rhs) {
            Integer d1 = lhs.getDuration();
            Integer d2 = rhs.getDuration();
            return d1.compareTo(d2);
        }
    }
}
