package io.github.louistsaitszho.erg2.gui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.util.GregorianCalendar;

import io.github.louistsaitszho.erg2.R;
import io.github.louistsaitszho.erg2.storage.DatabaseHelper;
import io.github.louistsaitszho.erg2.unit.Record;

public class NewRecordActivity extends ActionBarActivity {

    DatabaseHelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbh = new DatabaseHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_record);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_record, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case (R.id.DiscardAction):
                return true;
            case (R.id.ConfirmAction):
                Record r = getData();
                if (saveData(r) == true)
                    return true;
                else {
                    return false;
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public Record getData() {
        //TODO get data from each textfields
        int duration = getDuration();
        double distance = getDistance();
        int rating = getRating();
        GregorianCalendar startTime = getStartTime();

        Record r = new Record(startTime, duration, rating, distance);

        //TODO temporaty test
        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setText(r.toString());

        return r;
    }

    public int getDuration() {
        EditText durationHourET = (EditText) findViewById(R.id.editDurationHour);
        EditText durationMinuteET = (EditText) findViewById(R.id.editDurationMinute);
        EditText durationSecondET = (EditText) findViewById(R.id.editDurationSecond);
        String durationHourString = durationHourET.getText().toString();
        String durationMinuteString = durationMinuteET.getText().toString();
        String durationSecondString = durationSecondET.getText().toString();
        int duration = 0;
        int durationHour = Integer.parseInt(durationHourString);
        if (durationHour >= 0 && durationHour <= 24) {
            int durationMinute = Integer.parseInt(durationMinuteString);
            if (durationMinute >= 0 && durationMinute < 60) {
                int durationSecond = Integer.parseInt(durationSecondString);
                if (durationSecond >= 0 && durationSecond < 60) {
                    duration += durationHour * Record.HOUR_TO_SECOND * Record.SECOND_TO_MILLISECOND;
                    duration += durationMinute * Record.MINUTE_TO_SECOND * Record.SECOND_TO_MILLISECOND;
                    duration += durationSecond * Record.SECOND_TO_MILLISECOND;
                } else
                    duration = Record.INVALID_INT;
            } else
                duration = Record.INVALID_INT;
        } else
            duration = Record.INVALID_INT;
        return duration;
    }

    public GregorianCalendar getStartTime() {
        EditText dateYearET = (EditText) findViewById(R.id.editDateYear);
        EditText dateMonthET = (EditText) findViewById(R.id.editDateMonth);
        EditText dateDayET = (EditText) findViewById(R.id.editDateDay);
        EditText timeHourET = (EditText) findViewById(R.id.editTimeHour);
        EditText timeMinuteET = (EditText) findViewById(R.id.editTimeMinute);
        String dateYearString = dateYearET.getText().toString();
        String dateMonthString = dateMonthET.getText().toString();
        String dateDayString = dateDayET.getText().toString();
        String dateHourString = timeHourET.getText().toString();
        String dateMinuteString = timeMinuteET.getText().toString();
        int year = Integer.parseInt(dateYearString);
        int month = Integer.parseInt(dateMonthString);
        int day = Integer.parseInt(dateDayString);
        int hour = Integer.parseInt(dateHourString);
        int minute = Integer.parseInt(dateMinuteString);
        try {
            return new GregorianCalendar(year, month, day, hour, minute);
        } catch (IllegalArgumentException e) {
            return Record.INVALED_GC;
        }
    }

    public double getDistance() {
        EditText distanceET = (EditText) findViewById(R.id.editDistance);
        String distanceString = distanceET.getText().toString();
        double distance = Double.parseDouble(distanceString);
        if (distance < 0)
            distance = Record.INVALID_INT;
        return distance;
    }

    public int getRating() {
        EditText ratingET = (EditText) findViewById(R.id.editRating);
        String ratingString = ratingET.getText().toString();
        int rating = Integer.parseInt(ratingString);
        if (rating < 0)
            rating = Record.INVALID_INT;
        return rating;
    }



    public boolean saveData(Record r) {
        //TODO access Database
        return true;
    }
}
