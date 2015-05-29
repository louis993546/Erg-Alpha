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

import java.util.ArrayList;

import io.github.louistsaitszho.erg2.R;
import io.github.louistsaitszho.erg2.storage.HistoryDb;
import io.github.louistsaitszho.erg2.unit.Record;


public class HistoryActivity extends ActionBarActivity {

    HistoryDb hdb;

    @Override
    protected void onResume() {
        super.onResume();
        updateView();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        updateView();
    }

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
        int columnCount = c.getColumnCount();
        ArrayList<Record> ral = new ArrayList<>();
        output.append("count: " + count + "\n\n");
        if (count > 0) {
            do {
                ArrayList<String> incomingSAL = new ArrayList<>();
                for (int j = 1; j < columnCount; j++) {
                    incomingSAL.add(c.getString(j));
                }
                Record tempRecord = new Record(incomingSAL.get(0), Integer.parseInt(incomingSAL.get(2)), Integer.parseInt(incomingSAL.get(3)), Double.parseDouble(incomingSAL.get(1)));
                ral.add(tempRecord);
            }
            while (c.moveToNext());
            for (Record r : ral) {
                output.append(r.toString() + "\n\n");
            }
        }
        TextView tv2 = (TextView) findViewById(R.id.textView2);
        tv2.setText(output.toString());
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
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    public void addNewRecordActivity(View view) {
        Intent intent = new Intent(this, NewRecordActivity.class);
        startActivity(intent);
    }

}
