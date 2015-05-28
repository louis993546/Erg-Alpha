package io.github.louistsaitszho.erg2.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Louis on 5/26/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String HISTORIES_TABLE_NAME = "Histories";
    private static final String HISTORIES_TABLE_CREATE =
            "CREATE TABLE IF NOT EXIST " + HISTORIES_TABLE_NAME + "(" +
                    "ID         INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                    "YEAR       INTEGER NOT NULL" +
                    "MONTH      INTEGER NOT NULL, " +
                    "DAY        INTEGER NOT NULL, " +
                    "HOUR       INTEGER NOT NULL, " +
                    "MINUTE     INTEGER NOT NULL, " +
                    "DISTANCE   REAL    NOT NULL, " +
                    "RATING     INTEGER NOT NULL, " +
                    "DURATION   INTEGER NOT NULL);";
    private static String INSERT_NEW_RECORD = "INSERT INTO " + HISTORIES_TABLE_NAME +
            "(YEAR,MONTH,DAY,HOUR,MINUTE,DISTANCE,RATING,DURATION) VALUES ( " +
            "something);";

    public DatabaseHelper(Context context) {
        super(context, HISTORIES_TABLE_NAME, null, DATABASE_VERSION);    //TODO check if this is correct
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(HISTORIES_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private String GCToString(GregorianCalendar gc) {
        //do it with StringBuilder
        StringBuilder output = new StringBuilder();
        ArrayList<Integer> dateTime = new ArrayList<Integer>();

        dateTime.add(gc.get(Calendar.YEAR));
        dateTime.add(gc.get(Calendar.MONTH));
        dateTime.add(gc.get(Calendar.DAY_OF_MONTH));
        dateTime.add(gc.get(Calendar.HOUR));
        dateTime.add(gc.get(Calendar.MINUTE));

        for (int i:dateTime) {
            output.append(i);
            output.append('/');
        }
        output.deleteCharAt(output.length()-1);
        return output.toString();
    }

    private GregorianCalendar StringToGC(String s) {
        ArrayList<Integer> values = new ArrayList<Integer>();
        for (String value:s.split("/",5)) {
            values.add(Integer.parseInt(value));
        }
        return new GregorianCalendar(values.get(0), values.get(1), values.get(2), values.get(3), values.get(4));
    }
    //TODO Add
    //TODO Delete
    //TODO Modify
    //
}
