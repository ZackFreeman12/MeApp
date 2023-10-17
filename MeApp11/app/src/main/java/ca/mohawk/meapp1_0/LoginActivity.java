package ca.mohawk.meapp1_0;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

import io.getstream.chat.android.client.ChatClient;
import io.getstream.chat.android.client.models.User;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    Button btnReg;
    EditText etLoginEmail, etLoginPass;
    String storedEmail;
    String storedName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
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
                    String[] profileStrings = dbh.getProfileData(storedEmail);

                    storedName = toTitleCase(profileStrings[0]);

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
        ChatUsers chatUser = new ChatUsers(storedEmail,storedName);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("storedEmail",storedEmail);
        intent.putExtra("storedName",storedName);
        intent.putExtra("chatUser",chatUser);
        startActivity(intent);
    }
    public void loginNew(){
        ChatUsers chatUser = new ChatUsers(storedEmail,storedName);
        Intent intent = new Intent(this, IntroActivity.class);
        intent.putExtra("storedEmail",storedEmail);
        intent.putExtra("storedName",storedName);
        intent.putExtra("chatUser",chatUser);
        startActivity(intent);
    }

    private static String toTitleCase(String str) {

        if(str == null || str.isEmpty())
            return "";

        if(str.length() == 1)
            return str.toUpperCase();

        //split the string by space
        String[] parts = str.split(" ");

        StringBuilder sb = new StringBuilder( str.length() );

        for(String part : parts){

            if(part.length() > 1 )
                sb.append( part.substring(0, 1).toUpperCase() )
                        .append( part.substring(1).toLowerCase() );
            else
                sb.append(part.toUpperCase());

            sb.append(" ");
        }

        return sb.toString().trim();
    }
}