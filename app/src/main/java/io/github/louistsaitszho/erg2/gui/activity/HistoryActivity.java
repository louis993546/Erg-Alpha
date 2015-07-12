package io.github.louistsaitszho.erg2.gui.activity;

import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Outline;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.LinearLayout;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Comparator;

import io.github.louistsaitszho.erg2.R;
import io.github.louistsaitszho.erg2.adapter.HistoryAdapter;
import io.github.louistsaitszho.erg2.gui.dialog.SortingDialog;
import io.github.louistsaitszho.erg2.listener.cardClickListener;
import io.github.louistsaitszho.erg2.storage.HistoryDb;
import io.github.louistsaitszho.erg2.unit.Record;


public class HistoryActivity extends ActionBarActivity {

    public static final String TAG = HistoryActivity.class.getName();
    private HistoryDb hdb;
    private RecyclerView rv;
    private RecyclerView.Adapter rvAdapter;
    private RecyclerView.LayoutManager rvLM;
    private ArrayList<Record> ral;
    private Comparator<Record> sortingMode = Record.StartTimeComparatorDESC;

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
        RecyclerView historyRv = (RecyclerView) findViewById(R.id.HistoryRV);
        historyRv.addOnItemTouchListener(new cardClickListener(this, new cardClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                recordActivity(view, position);
            }
        }));
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
        switch (id) {
            case R.id.SortingAction:
                DialogFragment sd = new SortingDialog();
                sd.show(getFragmentManager(), "Sorting Mode");
                break;
            default:
                return true;
        }
        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    public void updateView() {
        ral = hdb.getRecords(sortingMode);
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

    public void newRecordActivity(View view) {
        Intent intent = new Intent(this, NewRecordActivity.class);
        startActivity(intent);
    }

    public void recordActivity(View view, int whichOne) {
        Intent intent = new Intent(this, RecordActivity.class);
        Gson gson = new Gson();
        String recordTBD = gson.toJson(ral.get(whichOne));
        intent.putExtra("theRecord", recordTBD);
        startActivity(intent);
    }


    public Comparator<Record> getSortingMode() {
        return sortingMode;
    }

    public void setSortingMode(Comparator<Record> newSM) {
        sortingMode = newSM;
    }
}
