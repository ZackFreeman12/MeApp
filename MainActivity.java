package ca.mohawk.dailyquestions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "==MainActivity==";

    MyDatabaseHelper mydbhelp = new MyDatabaseHelper(this);


    //private TextView questionz;
    //private MyDatabaseHelper dbHandler;
    //private String qOfDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SQLiteDatabase db = mydbhelp.getWritableDatabase();
        ContentValues values = new ContentValues();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button3 = (Button) findViewById(R.id.button3);
        FragmentContainerView fragAnswer = (FragmentContainerView) findViewById(R.id.fragmentContainerView);
        button3.setVisibility(View.INVISIBLE);
        fragAnswer.setVisibility(View.INVISIBLE);

        Log.d(TAG, "onCreate");



        Button button = (Button) findViewById(R.id.button);
        Button button2 = (Button) findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                button.setVisibility(View.GONE);
                button2.setVisibility(View.GONE);
                fragAnswer.setVisibility(View.VISIBLE);
                button3.setVisibility(View.VISIBLE);

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {


                                       @Override
                                       public void onClick(View view) {
                                           Toast toast = Toast.makeText(MainActivity.this, "Answer submitted to profile!", Toast.LENGTH_LONG);
                                           EditText ans = (EditText) findViewById(R.id.qAnswer);
                                           values.put(mydbhelp.COLUMN_QUESTIONS, "Do you own Crocs? Tell us the deets.");
                                           values.put(mydbhelp.COLUMN_ANSWERS, ans.getText().toString());
                                           long newrowID = db.insert(mydbhelp.TABLE_NAME, null, values);
                                           Log.d(TAG, "New ID" + newrowID);
                                           toast.show();


                                       }
                                   });




        //dbHandler = new MyDatabaseHelper(MainActivity.this);
        //dbHandler.insertQuestions("Do you own crocs? Tell us the deets.");
        //qOfDay = dbHandler.getQuestion();
    }




    /*
    public void questionsLoaded(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);






    }


     */


    /*
    public int populateAnswers(long id, String answer) {
        SQLiteDatabase db = mydbhelp.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(mydbhelp.COLUMN_ANSWERS, answer);
        int i = db.update(mydbhelp.TABLE_NAME,contentValues, mydbhelp.COLUMN_id + " = " + id, null);
        return i;
    }


     */



}