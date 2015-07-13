package io.github.louistsaitszho.erg2.utils;

/**
 * Created by Louis on 6/27/2015.
 */
public class TimeAgo {
    public final static long MILLISECOND = 1;
    public final static long SECOND = Consts.HOW_MANY_MS_IN_S * MILLISECOND;
    public final static long MINUTE = Consts.HOW_MANY_S_IN_MIN * SECOND;
    public final static long HOUR = Consts.HOW_MANY_MIN_IN_HOUR * MINUTE;
    public final static long DAY = Consts.HOW_MANY_HOUR_IN_DAY * HOUR;
    public final static long WEEK = Consts.HOW_MANY_DAY_IN_WEEK * DAY;
    public final static long MONTH = (long) (30.4375 * DAY);
    public final static long YEAR = (long) (365.25 * DAY);

    public static String toTimeAgo(long dateMsDiff) {
        if (dateMsDiff < MINUTE)
            return "Just now";
        else if (dateMsDiff < 2 * MINUTE)
            return "A minute ago";
        else if (dateMsDiff < 50 * MINUTE)
            return (dateMsDiff / MINUTE + " minutes ago");
        else if (dateMsDiff < 80 * MINUTE)
            return "An hour ago";
        else if (dateMsDiff < 24 * HOUR)
            return (dateMsDiff / HOUR + " hours ago");
        else if (dateMsDiff < 36 * HOUR)
            return ("A day ago");
        else if (dateMsDiff < WEEK)
            return (dateMsDiff / DAY + " days ago");
        else if (dateMsDiff < 2 * WEEK)
            return "A week ago";
        else if (dateMsDiff < MONTH)
            return (dateMsDiff / WEEK + " weeks ago");
        else if (dateMsDiff < 2 * MONTH)
            return "A month ago";
        else if (dateMsDiff < YEAR)
            return (dateMsDiff / MONTH + " months ago");
        else if (dateMsDiff < 2 * YEAR)
            return "A year ago";
        else return (dateMsDiff / YEAR + " years ago");
    }
}
