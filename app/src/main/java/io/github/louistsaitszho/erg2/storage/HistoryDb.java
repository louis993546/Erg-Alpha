package io.github.louistsaitszho.erg2.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import io.github.louistsaitszho.erg2.R;
import io.github.louistsaitszho.erg2.unit.Record;

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
        cv.put(HistoryContract.HistoryEntry.COLUMN_NAME_DATETIME, r.getStartTimeString(R.integer.START_DATETIME_STRING_STORAGE));
        cv.put(HistoryContract.HistoryEntry.COLUMN_NAME_DISTANCE, r.getDistance());
        cv.put(HistoryContract.HistoryEntry.COLUMN_NAME_DURATION, r.getDuration());
        cv.put(HistoryContract.HistoryEntry.COLUMN_NAME_RATING, r.getRating());
        return db.insert(HistoryContract.HistoryEntry.TABLE_NAME, null, cv);
    }

    public Cursor selectRecord() {
        String[] cols = new String[]{HistoryContract.HistoryEntry.COLUMN_NAME_ID,
                HistoryContract.HistoryEntry.COLUMN_NAME_DATETIME,
                HistoryContract.HistoryEntry.COLUMN_NAME_DISTANCE,
                HistoryContract.HistoryEntry.COLUMN_NAME_DURATION,
                HistoryContract.HistoryEntry.COLUMN_NAME_RATING};
        Cursor mCursor = db.query(true, HistoryContract.HistoryEntry.TABLE_NAME, cols, null, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
}
