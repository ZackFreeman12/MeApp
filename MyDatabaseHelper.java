package ca.mohawk.dailyquestions;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public static final String TAG = "==MyDbHelper==";


    public static final String DATABASE_NAME = "qAns.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "qAndAns";
    public static final String COLUMN_id = "id";
    public static final String COLUMN_QUESTIONS ="thequestions";
    public static final String COLUMN_ANSWERS = "theanswers";


    private static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_NAME + " ( " + COLUMN_id + " INTEGER PRIMARY KEY, " +
                    COLUMN_QUESTIONS + " TEXT, " + COLUMN_ANSWERS + " TEXT) ";



    public MyDatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(TAG, "constructor");

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate " + SQL_CREATE);
        db.execSQL(SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This is only called if the DATABASE_VERSION changes
        // Possible actions - delete table (ie DROP TABLE IF EXISTS mytable), then call onCreate
    }


    /*
    public void insertQuestions(String a) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_QUESTIONS, a);

        db.insert(TABLE_NAME, COLUMN_QUESTIONS,cv);
    }

    public String getQuestion() {
        SQLiteDatabase db=this.getReadableDatabase();
        String d = "Do you own crocs? Tell us the deets.";
        Cursor cursor = db.query(true, DATABASE_NAME,new String[] {COLUMN_QUESTIONS}, null, null, null, null, null, null);
        if(cursor != null) {
            cursor.moveToFirst();
            while(cursor.isAfterLast() != true) {
                @SuppressLint("Range") String c = cursor.getString(cursor.getColumnIndex(d));
                d = c;
            }
        }
        return d;
    }
    public void answerQuestion() {
        SQLiteDatabase db = this.getWritableDatabase();

    }

     */
}
