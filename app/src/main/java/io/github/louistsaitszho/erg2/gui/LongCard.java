package io.github.louistsaitszho.erg2.gui;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import io.github.louistsaitszho.erg2.R;

/**
 * Created by Louis on 7/17/2015.
 */
public class LongCard extends CardView {

    private String strDuration;
    private String strRating;
    private String strPer500;
    private String strDistance;
    private String strTimeAgo;
    private String strAvg500;
    private String strSpeed;
    private Context context;

    public LongCard(Context c) {
        super(c);
        context = c;
    }

    public LongCard(Context c, AttributeSet a) {
        super(c, a);
        context = c;
        getAttrs(a);
    }

    private void getAttrs(AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.LongCard, 0, 0);
        try {
            strDuration = a.getString(R.styleable.LongCard_durationText);
            strRating = a.getString(R.styleable.LongCard_ratingText);
            strPer500 = a.getString(R.styleable.LongCard_per500Text);
            strDistance = a.getString(R.styleable.LongCard_distanceText);
            strTimeAgo = a.getString(R.styleable.LongCard_timeAgoText);
            strAvg500 = a.getString(R.styleable.LongCard_avg500Text);
            strSpeed = a.getString(R.styleable.LongCard_speedText);
        } finally {
            a.recycle();
        }
    }

    private void inflate() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.long_card, this, true);

    }

    public void setStrDuration(String d) {
        strDuration = d;
    }

    public void setStrRating(String r) {
        strRating = r;
    }

    public void setStrPer500(String p5) {
        strPer500 = p5;
    }

    public void setStrDistance(String d) {
        strDistance = d;
    }

    public void setStrTimeAgo(String t) {
        strTimeAgo = t;
    }

    public void setStrAvg500(String a5) {
        strAvg500 = a5;
    }

    public void setStrSpeed(String s) {
        strSpeed = s;
    }
}
