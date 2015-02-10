package c.mars.sqlcipher_sample;

import android.content.Context;
import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;

import java.io.File;

/**
 * Created by mars on 2/4/15.
 */
public class CipherDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 6;
    public static final String DATABASE_NAME = "cipher.db";

    public CipherDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

//        File databaseFile = context.getDatabasePath(DATABASE_NAME);
//        databaseFile.mkdirs();
//        databaseFile.delete();
//
//        db = SQLiteDatabase.openOrCreateDatabase(databaseFile, PASSWORD, null);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CoreDb.NotesTable.SQL_CREATE_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(CoreDb.NotesTable.SQL_DELETE_TABLE);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}