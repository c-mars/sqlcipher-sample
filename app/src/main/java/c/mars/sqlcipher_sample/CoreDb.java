package c.mars.sqlcipher_sample;

import android.provider.BaseColumns;

import java.util.ArrayList;

/**
 * Created by mars on 2/9/15.
 */
public abstract class CoreDb {
    protected static final String[] DEFAULTS = {
            "one",
            "zebra",
            "record",
            "lightning",
            "enter sandman"
    };
    public void initWithDefaults() {
        for(String note:DEFAULTS) {
            insert(note);
        }
    }

    public abstract void insert(String note);
    public abstract ArrayList<String> getAll();

    public static abstract class NotesTable implements BaseColumns {
        public static final String TABLE_NAME = "notes_table";
        public static final String COLUMN_NAME_NOTE = "note";

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + NotesTable.TABLE_NAME + " (" +
                        NotesTable._ID + " INTEGER PRIMARY KEY, " +
                        NotesTable.COLUMN_NAME_NOTE + " TEXT)";

        public static final String SQL_DELETE_TABLE =
                "DROP TABLE IF EXISTS " + NotesTable.TABLE_NAME;
    }
}
