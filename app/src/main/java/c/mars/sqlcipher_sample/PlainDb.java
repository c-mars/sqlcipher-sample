package c.mars.sqlcipher_sample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by mars on 2/5/15.
 */
public class PlainDb extends CoreDb {
    private SQLiteDatabase db;

    public PlainDb(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    @Override
    public void insert(String note) {
        ContentValues values = new ContentValues();
        values.put(NotesTable.COLUMN_NAME_NOTE, note);

        db.insert(
                NotesTable.TABLE_NAME,
                null,
                values);
    }

    @Override
    public ArrayList<String> getAll() {
        Cursor c = db.query(NotesTable.TABLE_NAME, new String[]{NotesTable.COLUMN_NAME_NOTE}, null, null, null, null, null);
        ArrayList<String> notes = new ArrayList<>();
        c.moveToFirst();
        do {
            String n = c.getString(c.getColumnIndexOrThrow(NotesTable.COLUMN_NAME_NOTE));
            notes.add(n);
        } while (c.moveToNext());
        return notes;
    }
}
