package io.github.louistsaitszho.erg2.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Louis on 5/26/2015.
 */
public class Database extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String HISTORIES_TABLE_NAME = "Histories";
    private static final String HISTORIES_TABLE_CREATE = "CREATE TABLE " + HISTORIES_TABLE_NAME + "(" + "Something";    //FIXME not finished yet

    public Database(Context context) {
        super(context, HISTORIES_TABLE_NAME, null, DATABASE_VERSION);    //TODO check if this is correct
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(HISTORIES_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
