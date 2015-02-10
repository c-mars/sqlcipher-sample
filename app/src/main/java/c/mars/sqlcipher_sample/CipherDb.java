package c.mars.sqlcipher_sample;

import android.content.ContentValues;
import android.content.Context;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by mars on 2/5/15.
 */
public class CipherDb extends CoreDb {

    private SQLiteDatabase db;
    public static final String DATABASE_NAME = "cipher.db";
    public static final String PASSWORD = "test123";

    public CipherDb(Context context) {
        SQLiteDatabase.loadLibs(context);

        File databaseFile = context.getDatabasePath(DATABASE_NAME);
        databaseFile.mkdirs();
        databaseFile.delete();

        db = SQLiteDatabase.openOrCreateDatabase(databaseFile, PASSWORD, null);
        db.execSQL(NotesTable.SQL_CREATE_TABLE);
//        db.execSQL("create table "+NotesTable.TABLE_NAME+"("+NotesTable.COLUMN_NAME_NOTE+")");
    }

    @Override
    public void insert(String note) {
        ContentValues values = new ContentValues();
        values.put(NotesTable.COLUMN_NAME_NOTE, note);
        db.insert(NotesTable.TABLE_NAME, null, values);
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
        c.close();
        return notes;
    }
}
