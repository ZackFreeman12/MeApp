package ca.mohawk.meapp1_0;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {
    TextView tvName;
    TextView tvEmail;
    final char[] delimiters = { ' ', '_' };
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
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

            String name = toTitleCase(profileStrings[0]);
            tvName.setText(name);
            tvEmail.setText("Signed in as " + profileStrings[2]);
        }
        else{
            Toast.makeText(ProfileActivity.this, "No data found", Toast.LENGTH_SHORT).show();
        }




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