package ca.meapp.meappchat;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "questions.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "my_questions";
    private static final String COLUMN_id ="id";
    private static final String COLUMN_QUESTIONS="the_questions";
    private static final String COLUMN_ANSWERS = "the_answers";

    public MyDatabaseHelper(@Nullable Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_QUESTIONS + " TEXT," + COLUMN_ANSWERS + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

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
}
