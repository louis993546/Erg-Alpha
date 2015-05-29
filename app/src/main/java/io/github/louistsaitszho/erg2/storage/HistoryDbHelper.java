package io.github.louistsaitszho.erg2.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import io.github.louistsaitszho.erg2.storage.HistoryContract.HistoryEntry;

/**
 * Created by Louis on 5/29/2015.
 */
public class HistoryDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "History.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE IF EXISTS" + HistoryEntry.TABLE_NAME + " (" +
                    HistoryEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    HistoryEntry.COLUMN_NAME_DATETIME + TEXT_TYPE + COMMA_SEP +
                    HistoryEntry.COLUMN_NAME_DURATION + INTEGER_TYPE + COMMA_SEP +
                    HistoryEntry.COLUMN_NAME_DISTANCE + INTEGER_TYPE + COMMA_SEP +
                    HistoryEntry.COLUMN_NAME_RATING + INTEGER_TYPE + COMMA_SEP +
                    " )";

    //TODO finish this sentence
//    private static final String SQL_INSERT_ENTRIES =
//            "INSERT INTO " + HistoryEntry.TABLE_NAME + " (" +
//                    HistoryEntry.COLUMN_NAME_DATETIME + COMMA_SEP +
//                    HistoryEntry.COLUMN_NAME_DURATION + COMMA_SEP +
//                    HistoryEntry.COLUMN_NAME_DISTANCE + COMMA_SEP +
//                    HistoryEntry.COLUMN_NAME_RATING + ") VALUES ( "+

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + HistoryEntry.TABLE_NAME;

    public HistoryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO how to do this?
    }
}
