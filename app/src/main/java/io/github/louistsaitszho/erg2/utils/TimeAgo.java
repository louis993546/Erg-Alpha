package io.github.louistsaitszho.erg2.utils;

import java.util.Date;

/**
 * Created by Louis on 6/27/2015.
 */
public class TimeAgo {
    public final static long MILLISECOND = 1;
    public final static long SECOND = 1000 * MILLISECOND;
    public final static long MINUTE = 60 * SECOND;
    public final static long HOUR = 60 * MINUTE;
    public final static long DAY = 24 * HOUR;
    public final static long WEEK = 7 * DAY;
    public final static long MONTH = (int) (30.4375 * DAY);
    public final static long YEAR = (int) (365.25 * DAY);

    public static String toTimeAgo(Date dateDiff) {
        return toTimeAgo(dateDiff.getTime());
    }

    public static String toTimeAgo(long dateMsDiff) {
        if (dateMsDiff < MINUTE)
            return "Just now";
        if (dateMsDiff < 2 * MINUTE)
            return "A minute ago";
        if (dateMsDiff < 50 * MINUTE)
            return (dateMsDiff / MINUTE + " minutes ago");
        if (dateMsDiff < 80 * MINUTE)
            return "An hour ago";
        if (dateMsDiff < 24 * HOUR)
            return (dateMsDiff / HOUR + " hours ago");
        if (dateMsDiff < 36 * HOUR)
            return ("A day ago");
        if (dateMsDiff < WEEK)
            return (dateMsDiff / DAY + " days ago");
        if (dateMsDiff < 2 * WEEK)
            return "A week ago";
        if (dateMsDiff < MONTH)
            return (dateMsDiff / WEEK + " weeks ago");
        if (dateMsDiff < 2 * MONTH)
            return "A month ago";
        if (dateMsDiff < YEAR)
            return (dateMsDiff / MONTH + " months ago");
        if (dateMsDiff < 2 * YEAR)
            return "A year ago";
        return (dateMsDiff / YEAR + " years ago");
    }
}
