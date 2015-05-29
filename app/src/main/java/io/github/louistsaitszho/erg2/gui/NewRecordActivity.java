package io.github.louistsaitszho.erg2.gui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

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
        Record r = new Record();
        return r;
    }

    public boolean saveData(Record r) {
        //TODO access Database
        return true;
    }
}
