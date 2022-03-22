package ca.mohawk.meapp11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {
    TextView tvName;
    TextView tvEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();
        String storedEmail = intent.getStringExtra("storedEmail");

        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);

        DatabaseHelper dbh = new DatabaseHelper(ProfileActivity.this);

        boolean success = dbh.getProfileData(storedEmail) != null;
        if(success){
            String[] profileStrings = dbh.getProfileData(storedEmail);

            tvName.setText(profileStrings[0]);
            tvEmail.setText(profileStrings[2]);
        }
        else{
            Toast.makeText(ProfileActivity.this, "No data found", Toast.LENGTH_SHORT).show();
        }




    }
}