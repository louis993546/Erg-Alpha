package io.github.louistsaitszho.erg2.gui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import io.github.louistsaitszho.erg2.R;
import io.github.louistsaitszho.erg2.unit.Record;

/**
 * Created by Louis on 30/5/15.
 */
public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder2> {

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
        hour = ms / HOUR_TO_MS;
        ms = ms % HOUR_TO_MS;
        minute = ms / MINUTE_TO_MS;
        ms = ms % MINUTE_TO_MS;
        second = ms / SECOND_TO_MS;
        output.append(hour).append(":").append(minute).append(":").append(second);
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
        return r.startTimeToString(2);
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
