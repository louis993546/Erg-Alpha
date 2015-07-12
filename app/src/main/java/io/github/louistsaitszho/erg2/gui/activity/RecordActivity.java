package io.github.louistsaitszho.erg2.gui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;

import io.github.louistsaitszho.erg2.R;
import io.github.louistsaitszho.erg2.unit.Record;
import io.github.louistsaitszho.erg2.utils.Consts;

public class RecordActivity extends ActionBarActivity {
  public final static String TAG = RecordActivity.class.getName();

  private Record theRecord;

  private TextView tvStartDateTime;
  private TextView tvDuration;
  private TextView tvDistance;
  private TextView tvRating;
  private TextView tvSpeed;
  private TextView tvPer500m;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_record);
    Gson gson = new Gson();
    theRecord = gson.fromJson(getIntent().getStringExtra("theRecord"), Record.class);

    tvStartDateTime = (TextView) findViewById(R.id.startDateTimeTV);
    tvDuration = (TextView) findViewById(R.id.durationTV);
    tvDistance = (TextView) findViewById(R.id.distanceTV);
    tvRating = (TextView) findViewById(R.id.ratingTV);
    tvSpeed = (TextView) findViewById(R.id.speedTV);
    tvPer500m = (TextView) findViewById(R.id.per500mTV);

    theRecord.setContext(this);
    tvStartDateTime.setText(theRecord.getStartTimeString(Consts.START_DATETIME_STRING_EXACT));
    tvDuration.setText(theRecord.getDurationString());
    tvDistance.setText(theRecord.getDistanceString());
    tvRating.setText(theRecord.getRatingString());
    tvSpeed.setText(theRecord.getSpeedString());
    tvPer500m.setText(theRecord.getPer500mString());

  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_record, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
//    if (id == R.id.action_settings) {
//      return true;
//    }

    return super.onOptionsItemSelected(item);
  }
}
