package io.github.louistsaitszho.erg2.gui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.GregorianCalendar;

import io.github.louistsaitszho.erg2.R;
import io.github.louistsaitszho.erg2.storage.HistoryDb;
import io.github.louistsaitszho.erg2.unit.Record;

public class NewRecordActivity extends ActionBarActivity {

    public static final String TAG = NewRecordActivity.class.getName();
    HistoryDb hdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_record);
        hdb = new HistoryDb(this);
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
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setTitle("Discard changes");
                builder1.setMessage("All changes will be discard.");
                builder1.setCancelable(true);
                builder1.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                finish();
                            }
                        });
                builder1.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = builder1.create();
                alert11.show();
                return true;
            case (R.id.ConfirmAction):
                try {
                    Record r = getData();
                    saveData(r);
                    finish();
                    return true;
                } catch (NullPointerException | NumberFormatException e) {
                    return false;
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * @return a record value according to user input, but if user input is invalid, the return
     * instance of record will be a default instance of Record (30 minutes 7000 m)
     * TODO: instead of returning a default record, it should throw some exception out to notify
     * user to correct their mistakes
     */
    public Record getData() {
        try {
            int duration = getDuration();
            double distance = getDistance();
            GregorianCalendar startTime = getStartTime();
            Log.d(TAG, startTime.toString());
            int rating = getRating();
            return new Record(startTime, duration, rating, distance);
        } catch (NullPointerException | NumberFormatException e) {
            throw new NullPointerException("Invalid input");
        }
    }

    public void invalidInputAlertDialog(String title, String message) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle(title);
        adb.setMessage(message);
        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog ad = adb.create();
        ad.show();
    }

    /**
     * @return duration user have specified in interger of millisecond
     * TODO change it to some kind of date time picker
     */
    public int getDuration() {
        EditText durationHourET = (EditText) findViewById(R.id.editDurationHour);
        EditText durationMinuteET = (EditText) findViewById(R.id.editDurationMinute);
        EditText durationSecondET = (EditText) findViewById(R.id.editDurationSecond);
        String durationHourString = durationHourET.getText().toString();
        String durationMinuteString = durationMinuteET.getText().toString();
        String durationSecondString = durationSecondET.getText().toString();
        int duration = 0;
        try {
            int durationHour = Integer.parseInt(durationHourString);
            if (durationHour >= 0 && durationHour <= 24) {
                int durationMinute = Integer.parseInt(durationMinuteString);
                if (durationMinute >= 0 && durationMinute < 60) {
                    double durationSecond = Double.parseDouble(durationSecondString);
                    if (durationSecond >= 0 && durationSecond < 60) {
                        if ((durationSecond == 0) && (durationMinute == 0) && (durationHour == 0)) {
                            invalidInputAlertDialog("Invalid input", "Duration must not be zero");
                            Log.d(TAG, "Input duration is 0");
                            throw new NullPointerException("Invalid input");
                        }
                        else
                        {
                            duration += durationHour * Record.HOUR_TO_SECOND * Record.SECOND_TO_MILLISECOND;
                            duration += durationMinute * Record.MINUTE_TO_SECOND * Record.SECOND_TO_MILLISECOND;
                            duration += durationSecond * Record.SECOND_TO_MILLISECOND;
                            return duration;
                        }
                    } else {
                        invalidInputAlertDialog("Invalid input", "Please input a valid duration(second).");
                        Log.d(TAG, "Invalid duration(second)");
                        throw new NullPointerException("Invalid duration(second)");
                    }
                } else {
                    invalidInputAlertDialog("Invalid input", "Please input a valid duration(minute).");
                    Log.d(TAG, "Invalid duration(minute)");
                    throw new NullPointerException("Invalid duration(minute)");
                }
            } else {
                invalidInputAlertDialog("Invalid input", "Please input a valid duration(hour).");
                Log.d(TAG, "Invalid duration(hour)");
                throw new NullPointerException("Invalid duration(Hour)");
            }
        } catch (NumberFormatException e) {
            invalidInputAlertDialog("Invalid input", "Please input a valid duration.");
            Log.d(TAG, "Invalid duration(Number format exception)");
            throw new NullPointerException("Invalid duration: " + e.toString());
        }
    }

    /**
     * @throws NullPointerException when GregorianCalendar constructor fail to create a instance
     * of time based on user inputs
     * @return a instance of GregorianCalendar of user specified start date time
     * TODO something is probably wrong here
     * TODO change it to datapicker
     * TODO create a lot of invalidInputAlertDialog here for null input
     */
    public GregorianCalendar getStartTime() {
        try {
            int year = getEditTextInt(R.id.editDateYear);
            int month = getEditTextInt(R.id.editDateMonth)-1;
            int day = getEditTextInt(R.id.editDateDay);
            int hour = getEditTextInt(R.id.editTimeHour);
            int minute = getEditTextInt(R.id.editTimeMinute);
            Log.d(TAG, "" + year + month + day + hour + minute);
            return new GregorianCalendar(year, month, day, hour, minute);
        } catch (NumberFormatException | NullPointerException e) {
            Log.d(TAG, "Exception in getStartTime");
            invalidInputAlertDialog("Invalid input", "Please input a valid start date and time.");
            throw new NullPointerException("Invalid start date time");
        }
    }

    private int getEditTextInt(int id) {
        EditText etf = (EditText) findViewById(id);
        String etfString = etf.getText().toString();
        if (etfString != null) {
            if (etfString.length() > 0) {
                return Integer.parseInt(etfString);
            }
            else {
                Log.d(TAG, "getEditTextInt with length == 0");
                throw new NumberFormatException("Length == 0");
            }
        }
        else {
            Log.d(TAG, "getEditTextInt with no input");
            throw new NullPointerException("EditText field is empty");
        }
    }

    /**
     * @throws NumberFormatException when user input is not a valid number or value is smaller
     * than 0
     * @return distance in floating point (double)
     */
    public double getDistance() {
        EditText distanceET = (EditText) findViewById(R.id.editDistance);
        String distanceString = distanceET.getText().toString();
        try {
            double distance = Double.parseDouble(distanceString);
            if (distance <= 0) {
                Log.d(TAG, "Distance <= 0");
                invalidInputAlertDialog("Invalid input", "Please input a valid distance.");
                throw new NumberFormatException("Invalid distance");
            }
            else {
                return distance;
            }
        } catch (NumberFormatException e) {
            Log.d(TAG, "Distance number format exception");
            invalidInputAlertDialog("Invalid input", "Please input a valid distance.");
            throw new NumberFormatException("Invalid distance: " + e.toString());
        }
    }

    /**
     * @throws NumberFormatException when user input is not a valid integer or value is small than 0
     * @return rating in int
     */
    public int getRating() {
        EditText ratingET = (EditText) findViewById(R.id.editRating);
        String ratingString = ratingET.getText().toString();
        try {
            int rating = Integer.parseInt(ratingString);
            if (rating < 0) {
                invalidInputAlertDialog("Invalid input", "Please input a valid rating.");
                throw new NumberFormatException("Invalid rating");
            }
            return rating;
        } catch (NumberFormatException e) {
            invalidInputAlertDialog("Invalid input", "Please input a valid rating.");
            throw new NumberFormatException("Invalid rating: " + e.toString());
        }
    }

    public boolean saveData(Record r) {
        hdb.addRecord(r);
        return true;
    }
}
