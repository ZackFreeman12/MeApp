package ca.mohawk.meapp1_0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    Button btnReg;
    EditText etLoginEmail, etLoginPass;
    String storedEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnLogin);
        btnReg = findViewById(R.id.btnReg);
        etLoginEmail = findViewById(R.id.etLoginEmail);
        etLoginPass = findViewById(R.id.etLoginPass);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String email = etLoginEmail.getText().toString();
                final String pass = etLoginPass.getText().toString();

                DatabaseHelper dbh = new DatabaseHelper(LoginActivity.this);

                boolean success = dbh.checkEmailPassword(email,pass) != null;
                if(success){
                    storedEmail = dbh.checkEmailPassword(email,pass);

                    if(dbh.isNewUser(storedEmail)){
                        loginNew();
                    }
                    else{
                        login();
                    }


                }
                else{
                    Toast.makeText(LoginActivity.this, "No user found", Toast.LENGTH_SHORT).show();
                }


            }
        });

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toRegister();
            }
        });

    }
    public void toRegister (){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
    public void login(){

        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra("storedEmail",storedEmail);
        startActivity(intent);
    }
    public void loginNew(){
        Intent intent = new Intent(this, IntroActivity.class);
        intent.putExtra("storedEmail",storedEmail);
        startActivity(intent);
    }
}