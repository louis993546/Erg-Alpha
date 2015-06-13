package io.github.louistsaitszho.erg2.storage;

import android.provider.BaseColumns;

/**
 * Created by Louis on 5/29/2015.
 */
public final class HistoryContract {
    public HistoryContract() {}

    public static abstract class HistoryEntry implements BaseColumns {
        public static final String TABLE_NAME = "history";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_DATETIME = "datetime";
        public static final String COLUMN_NAME_DURATION = "duration";
        public static final String COLUMN_NAME_DISTANCE = "distance";
        public static final String COLUMN_NAME_RATING = "rating";
    }
}

