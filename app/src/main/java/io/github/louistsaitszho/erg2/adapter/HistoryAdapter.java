package io.github.louistsaitszho.erg2.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import io.github.louistsaitszho.erg2.R;
import io.github.louistsaitszho.erg2.unit.Record;
import io.github.louistsaitszho.erg2.utils.Consts;

/**
 * Created by Louis on 30/5/15.
 */
public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder2> {

    public static final String TAG = HistoryAdapter.class.getName();
    private static final int HOUR_TO_MS = 3600000;
    private static final int MINUTE_TO_MS = 60000;
    private static final int SECOND_TO_MS = 1000;
    private ArrayList<Record> ral;

    public HistoryAdapter(ArrayList<Record> r) {
        ral = r;
    }

    @Override
    public void onBindViewHolder(ViewHolder2 vh, int i) {
        Record r = ral.get(i);

        vh.durTV.setText(durationToString(r.getDuration()));
        vh.disTV.setText(distanceToString(r.getDistance()));
        vh.ratTV.setText(ratingToString(r.getRating()));
        vh.perTV.setText(p5mToString(r.per500()));
        vh.sdtTV.setText(startDateTimeToString(r));
    }

    @Override
    public ViewHolder2 onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card, viewGroup, false);
        return new ViewHolder2(itemView);
    }


    @Override
    public int getItemCount() {
        return ral.size();
    }

    public String durationToString(int ms) {
        int hour;
        int minute;
        int second;
        StringBuilder output = new StringBuilder();
        hour = ms / Consts.HOW_MANY_MS_IN_S / Consts.HOW_MANY_S_IN_MIN / Consts.HOW_MANY_MIN_IN_HOUR;
        ms = ms % (Consts.HOW_MANY_MS_IN_S * Consts.HOW_MANY_S_IN_MIN * Consts.HOW_MANY_MIN_IN_HOUR);
        minute = ms / (Consts.HOW_MANY_MS_IN_S * Consts.HOW_MANY_S_IN_MIN);
        ms = ms % (Consts.HOW_MANY_MS_IN_S * Consts.HOW_MANY_S_IN_MIN);
        second = ms / Consts.HOW_MANY_MS_IN_S;
        ms = (ms % Consts.HOW_MANY_MS_IN_S) / 100;
        if (hour != 0) {
            output.append(hour).append(":");
        }
        String minuteString;
        if (minute < 10) {
            minuteString = "0" + minute;
        } else {
            minuteString = "" + minute;
        }
        String secondString;
        if (second < 10) {
            secondString = "0" + second;
        } else {
          secondString = "" + second;
        }
        output.append(minuteString).append(":").append(secondString).append(".").append(ms);
        return output.toString();
    }

    public String p5mToString(int ms) {
        int minute;
        int second;
        StringBuilder secondString = new StringBuilder();
        int millisecond;
        StringBuilder output = new StringBuilder();
        minute = ms / MINUTE_TO_MS;
        ms = ms % MINUTE_TO_MS;
        second = ms / SECOND_TO_MS;
        if (second < 10) {
            secondString.append("0").append(second);
        } else {
            secondString.append(second);
        }
        ms = ms % SECOND_TO_MS;
        millisecond = ms / 100;
        output.append(minute).append(":").append(secondString).append(".").append(millisecond);    //DO NOT PUT UNIT HERE
        return output.toString();
    }

    public String ratingToString(int r) {
        return (r + " s/min");
    }

    public String distanceToString(double d) {
        return (d + "m");
    }

    public String startDateTimeToString(Record r) {
        return r.getStartTimeString(Consts.START_DATETIME_STRING_DIFFERENCE);
    }

    public static class ViewHolder2 extends RecyclerView.ViewHolder {
        protected TextView durTV;
        protected TextView disTV;
        protected TextView ratTV;
        protected TextView perTV;
        protected TextView sdtTV;

        public ViewHolder2(View v) {
            super(v);
            durTV = (TextView) v.findViewById(R.id.DurationTV);
            disTV = (TextView) v.findViewById(R.id.DistanceTV);
            ratTV = (TextView) v.findViewById(R.id.RatingTV);
            perTV = (TextView) v.findViewById(R.id.p5mValueTV);
            sdtTV = (TextView) v.findViewById(R.id.startDateTimeTV);
        }
    }

}
