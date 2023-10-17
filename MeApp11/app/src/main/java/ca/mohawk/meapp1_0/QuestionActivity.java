package ca.mohawk.meapp1_0;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class QuestionActivity extends AppCompatActivity {

    public static final String TAG = "==MainActivity==";
    String storedEmail;



    //private TextView questionz;
    //private MyDatabaseHelper dbHandler;
    //private String qOfDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Button btnThree = findViewById(R.id.btnThree);
        FragmentContainerView fragAnswer = findViewById(R.id.fragmentContainerView);
        btnThree.setVisibility(View.INVISIBLE);
        fragAnswer.setVisibility(View.INVISIBLE);

        Log.d(TAG, "onCreate");

        Intent intent = getIntent();
        storedEmail = intent.getStringExtra("storedEmail");
        Button btnOne = findViewById(R.id.btnOne);
        Button btnTwo = findViewById(R.id.btnTwo);

        btnOne.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                btnOne.setVisibility(View.GONE);
                btnTwo.setVisibility(View.GONE);
                fragAnswer.setVisibility(View.VISIBLE);
                btnThree.setVisibility(View.VISIBLE);

            }
        });
        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(QuestionActivity.this, MainActivity.class);
                intent.putExtra("storedEmail",storedEmail);
                startActivity(intent);
            }
        });
        btnThree.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(QuestionActivity.this, "Answer submitted to profile!", Toast.LENGTH_LONG);
                EditText ans = findViewById(R.id.qAnswer);

                DatabaseHelper dbh = new DatabaseHelper(QuestionActivity.this);
                dbh.updateProfile(storedEmail,null,null,null,null,ans.getText().toString());
//                values.put(dbh.COLUMN_QUESTIONS, "Do you own Crocs? Tell us the deets.");
//                values.put(dbh.COLUMN_ANSWERS, ans.getText().toString());
//                long newrowID = db.insert(dbh.TABLE_NAME, null, values);
//                Log.d(TAG, "New ID" + newrowID);
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