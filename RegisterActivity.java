package ca.mohawk.meapp11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    Button btnSub;
    Button btnBackToLog;
    EditText etFirst, etLast, etEmail, etPass, etAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnBackToLog = findViewById(R.id.btnBackToLog);
        btnSub = findViewById(R.id.btnSub);
        etEmail = findViewById(R.id.etEmail);
        etFirst = findViewById(R.id.etFirst);
        etLast = findViewById(R.id.etLast);
        etPass = findViewById(R.id.etPass);
        etAge = findViewById(R.id.etAge);


        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final String firstName=etFirst.getText().toString();
                final String lastName=etLast.getText().toString();
                final String age=etAge.getText().toString();
                final String email=etEmail.getText().toString();
                final String pass=etPass.getText().toString();
                boolean isValidEmail = !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();


                if(firstName.length()==0 || lastName.length()==0)
                {
                    if(lastName.length() > 0) {
                        etFirst.requestFocus();
                        etFirst.setError("FIELD CANNOT BE EMPTY");
                    }
                    else if(firstName.length() > 0){
                        etLast.requestFocus();
                        etLast.setError("FIELD CANNOT BE EMPTY");
                    }
                    else{
                        etFirst.requestFocus();
                        etFirst.setError("FIELD CANNOT BE EMPTY");
                        etLast.requestFocus();
                        etLast.setError("FIELD CANNOT BE EMPTY");
                    }

                }
                else if(!firstName.matches("[a-zA-Z ]+") || !lastName.matches("[a-zA-Z ]+"))
                {
                    etFirst.requestFocus();
                    etFirst.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                    etLast.requestFocus();
                    etLast.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                }
                if(age.length()==0)
                {
                    etAge.requestFocus();
                    etAge.setError("FIELD CANNOT BE EMPTY");
                }
                if(email.length()==0)
                {
                    etEmail.requestFocus();
                    etEmail.setError("FIELD CANNOT BE EMPTY");
                }
                else if (!isValidEmail){
                    etEmail.requestFocus();
                    etEmail.setError("ENTER A VALID EMAIL ADDRESS");
                }
                if(pass.length() < 6 || pass.length() > 24)
                {
                    etPass.requestFocus();
                    etPass.setError("PASSWORD MUST BE BETWEEN 6-24 CHARACTERS");
                }

                else
                {
                    ProfileModel profileModel = new ProfileModel(firstName + " " + lastName,
                            age,email,pass);

                    DatabaseHelper dbh = new DatabaseHelper(RegisterActivity.this);

                    boolean success = dbh.addOne(profileModel);

                    Toast.makeText(RegisterActivity.this, "SUCC", Toast.LENGTH_SHORT).show();
                }









            }
        });

        btnBackToLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toLog();
            }
        });

    }

    public void toLog(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}