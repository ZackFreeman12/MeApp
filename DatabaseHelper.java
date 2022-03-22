package ca.mohawk.meapp11;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Debug;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String PROFILE_TABLE = "PROFILE_TABLE";
    public static final String COLUMN_USER_NAME = "COLUMN_USER_NAME";
    public static final String COLUMN_USER_AGE = "COLUMN_USER_AGE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_USER_EMAIL = "COLUMN_USER_EMAIL";
    public static final String COLUMN_USER_PASSWORD = "COLUMN_USER_PASSWORD";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "profile.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creates a new database when this class is first referenced and will not if it already exits
        String createTable = "CREATE TABLE  PROFILE_TABLE (  COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "COLUMN_USER_NAME  TEXT, " +
                "COLUMN_USER_AGE INTEGER, " +
                "COLUMN_USER_EMAIL TEXT, " +
                "COLUMN_USER_PASSWORD TEXT)";

        db.execSQL(createTable);
    }

    //mandatory method implementation for SQLiteOpenHelper SuperClass
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOne(ProfileModel profileModel) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USER_NAME, profileModel.getUserName());
        cv.put(COLUMN_USER_AGE, profileModel.getAge());
        cv.put(COLUMN_USER_EMAIL, profileModel.getEmail());
        cv.put(COLUMN_USER_PASSWORD, profileModel.getPass());

        long insert = db.insert(PROFILE_TABLE, null, cv);

        return insert != -1;
    }

    public String checkEmailPassword(String email, String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from PROFILE_TABLE where COLUMN_USER_EMAIL=? and COLUMN_USER_PASSWORD=?", new String[] {email,pass});

        if(cursor.moveToFirst()){
            Log.d("TAG",cursor.getString(2));
            return email;
        }
        else{
            return null;
        }

    }

    public String[] getProfileData (String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from PROFILE_TABLE where COLUMN_USER_EMAIL=?",new String[] {email});

        if(cursor.moveToFirst()){
            return new String[] {cursor.getString(1),cursor.getString(2),cursor.getString(3)};
        }
        else{
            return null;
        }

    }

}
