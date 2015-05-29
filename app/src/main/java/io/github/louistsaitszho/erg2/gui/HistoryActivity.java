package io.github.louistsaitszho.erg2.gui;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Outline;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.TextView;

import io.github.louistsaitszho.erg2.R;
import io.github.louistsaitszho.erg2.storage.HistoryContract;
import io.github.louistsaitszho.erg2.storage.HistoryDb;


public class HistoryActivity extends ActionBarActivity {

    HistoryDb hdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        //TODO load records from database
        hdb = new HistoryDb(this);
        updateView();
        //TODO create FAB
        ViewOutlineProvider vop = new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                int size = getResources().getDimensionPixelSize(R.dimen.fab_size);
                outline = new Outline();
            }
        };
        findViewById(R.id.fab).setOutlineProvider(vop);
    }

    public void updateView() {
        Cursor c = hdb.selectRecord();
        StringBuilder output = new StringBuilder();
        int count = c.getCount();
        if (count > 0) {
            output.append("count: " + count + "\n\n");
            while (c.moveToNext()) {
                output.append("ID: " + c.getColumnIndexOrThrow(HistoryContract.HistoryEntry.COLUMN_NAME_ID) + "\n");
                output.append("Duration: " + c.getColumnIndexOrThrow(HistoryContract.HistoryEntry.COLUMN_NAME_DURATION) + "\n");
                output.append("Distance: " + c.getColumnIndexOrThrow(HistoryContract.HistoryEntry.COLUMN_NAME_DISTANCE) + "\n");
                output.append("Start Date Time: " + c.getColumnIndexOrThrow(HistoryContract.HistoryEntry.COLUMN_NAME_DATETIME) + "\n");
                output.append("Rating: " + c.getColumnIndexOrThrow(HistoryContract.HistoryEntry.COLUMN_NAME_RATING) + "\n\n");
            }
            TextView tv2 = (TextView) findViewById(R.id.textView2);
            tv2.setText(output.toString());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_history, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            addNewSettingActivity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addNewSettingActivity() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
        //TODO reload everything???
    }

    public void addNewRecordActivity(View view) {
        Intent intent = new Intent(this, NewRecordActivity.class);
        startActivity(intent);
        updateView();
        //TODO update from database
    }

}
