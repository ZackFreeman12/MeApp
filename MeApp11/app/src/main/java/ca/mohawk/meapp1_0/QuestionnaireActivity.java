package ca.mohawk.meapp1_0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class QuestionnaireActivity extends AppCompatActivity {

    public static final String TAG = "==MainActivity==";

    DatabaseHelper mydbhelp = new DatabaseHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionare);

        ListView listView = findViewById(R.id.single_list_view_item);

        ArrayList<String> stringArrayList = setArrayList();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(QuestionnaireActivity.this,
                android.R.layout.simple_list_item_single_choice,
                stringArrayList);

        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setAdapter(arrayAdapter);
        final String[] answer = {""};
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                String items = (String)parent.getItemAtPosition(position);
                answer[0] = items.toString();
                Toast.makeText(QuestionnaireActivity.this, "Select Choice List is " + items,
                        Toast.LENGTH_SHORT).show();
            }
        });

        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(QuestionnaireActivity.this, "Answer submitted to profile!", Toast.LENGTH_LONG);
//                SQLiteDatabase db = mydbhelp.getWritableDatabase();
//                ContentValues values = new ContentValues();
//                values.put(mydbhelp.COLUMN_QUESTION, "What are your dealbreakers?" );
//                values.put(mydbhelp.COLUMN_ANSWERS, answer[0]);
//                long newrowID = db.insert(mydbhelp.TABLE_NAME, null, values);
//                Log.d(TAG, "New ID" + newrowID);
                toast.show();

            }
        });



    }



    private ArrayList<String> setArrayList() {


        ArrayList<String> arrayList = new ArrayList();
        arrayList.add(getResources().getString(R.string.a1));
        arrayList.add(getResources().getString(R.string.a2));
        arrayList.add(getResources().getString(R.string.a3));
        arrayList.add(getResources().getString(R.string.a4));
        arrayList.add(getResources().getString(R.string.a5));

        return arrayList;


    }


}