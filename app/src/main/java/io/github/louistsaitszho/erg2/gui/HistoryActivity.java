package io.github.louistsaitszho.erg2.gui;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Outline;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Collections;

import io.github.louistsaitszho.erg2.R;
import io.github.louistsaitszho.erg2.storage.HistoryDb;
import io.github.louistsaitszho.erg2.unit.Record;


public class HistoryActivity extends ActionBarActivity {

    public static final String TAG = HistoryActivity.class.getName();
    private HistoryDb hdb;
    private RecyclerView rv;
    private RecyclerView.Adapter rvAdapter;
    private RecyclerView.LayoutManager rvLM;
    private ArrayList<Record> ral;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.actual_title_activity_history);
        setContentView(R.layout.activity_history);
        hdb = new HistoryDb(this);
        updateView();
        ViewOutlineProvider vop = new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                int size = getResources().getDimensionPixelSize(R.dimen.fab_size);
                outline = new Outline();
            }
        };
        findViewById(R.id.fab).setOutlineProvider(vop);
    }

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

    public ArrayList<Record> getRecords() {
        Cursor c = hdb.selectRecord();
        int count = c.getCount();
        int columnCount = c.getColumnCount();
        ArrayList<Record> ral = new ArrayList<>();
        if (count > 0) {
            do {
                ArrayList<String> incomingSAL = new ArrayList<>();
                for (int j = 1; j < columnCount; j++) {
                    incomingSAL.add(c.getString(j));
                }
                Log.d(TAG, incomingSAL.get(0));
                Record tempRecord = new Record(incomingSAL.get(0), Integer.parseInt(incomingSAL.get(2)), Integer.parseInt(incomingSAL.get(3)), Double.parseDouble(incomingSAL.get(1)));
                Log.d(TAG, tempRecord.startTimeToString(R.integer.START_DATETIME_STRING_EXACT));
                ral.add(tempRecord);
            }
            while (c.moveToNext());
        }
        //TODO can we sort them here?
        Collections.sort(ral, Record.StartTimeComparatorDESC);
        return ral;
    }

    public void updateView() {
        ral = getRecords();
        rv = (RecyclerView) findViewById(R.id.HistoryRV);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayout.VERTICAL);
        rv.setLayoutManager(llm);
        rvAdapter = new HistoryAdapter(ral);
        rv.setAdapter(rvAdapter);

        ViewOutlineProvider vop = new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                int size = getResources().getDimensionPixelSize(R.dimen.fab_size);
                outline = new Outline();
            }
        };
        findViewById(R.id.fab).setOutlineProvider(vop);
    }



    public void addNewRecordActivity(View view) {
        Intent intent = new Intent(this, NewRecordActivity.class);
        startActivity(intent);
    }

}
