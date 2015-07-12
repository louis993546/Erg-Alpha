package io.github.louistsaitszho.erg2.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import io.github.louistsaitszho.erg2.unit.Record;
import io.github.louistsaitszho.erg2.utils.Consts;

import static io.github.louistsaitszho.erg2.storage.HistoryContract.HistoryEntry.COLUMN_NAME_DATETIME;
import static io.github.louistsaitszho.erg2.storage.HistoryContract.HistoryEntry.COLUMN_NAME_DISTANCE;
import static io.github.louistsaitszho.erg2.storage.HistoryContract.HistoryEntry.COLUMN_NAME_DURATION;
import static io.github.louistsaitszho.erg2.storage.HistoryContract.HistoryEntry.COLUMN_NAME_ID;
import static io.github.louistsaitszho.erg2.storage.HistoryContract.HistoryEntry.COLUMN_NAME_RATING;
import static io.github.louistsaitszho.erg2.storage.HistoryContract.HistoryEntry.TABLE_NAME;

/**
 * Created by Louis on 29/5/15.
 */
public class HistoryDb {
    private HistoryDbHelper hdh;
    private SQLiteDatabase db;

    public HistoryDb(Context context) {
        hdh = new HistoryDbHelper(context);
        db = hdh.getWritableDatabase();
    }

    public long addRecord(Record r) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME_DATETIME, r.getStartTimeString(Consts.START_DATETIME_STRING_STORAGE));
        cv.put(COLUMN_NAME_DISTANCE, r.getDistance());
        cv.put(COLUMN_NAME_DURATION, r.getDuration());
        cv.put(COLUMN_NAME_RATING, r.getRating());
        return db.insert(TABLE_NAME, null, cv);
    }

    public Cursor selectRecord() {
        String[] cols = new String[]{COLUMN_NAME_ID,
                COLUMN_NAME_DATETIME,
                COLUMN_NAME_DISTANCE,
                COLUMN_NAME_DURATION,
                COLUMN_NAME_RATING};
        Cursor mCursor = db.query(true, TABLE_NAME, cols, null, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public ArrayList<Record> getRecords(Comparator<Record> sortingMode) {
        Cursor c = selectRecord();
        int count = c.getCount();
        int columnCount = c.getColumnCount();
        ArrayList<Record> ral = new ArrayList<>();
        if (count > 0) {
            do {
                ArrayList<String> incomingSAL = new ArrayList<>();
                for (int j = 1; j < columnCount; j++) {
                    incomingSAL.add(c.getString(j));
                }
                Record tempRecord = new Record(incomingSAL.get(0), Integer.parseInt(incomingSAL.get(2)), Integer.parseInt(incomingSAL.get(3)), Double.parseDouble(incomingSAL.get(1)));
                tempRecord.setID(Integer.parseInt(c.getString(0)));
                ral.add(tempRecord);
            }
            while (c.moveToNext());
        }
        Collections.sort(ral, sortingMode);
        return ral;
    }

    public int removeRecord(int recordID) {
        return db.delete(TABLE_NAME, COLUMN_NAME_ID + "=" + recordID, null);
    }
}
