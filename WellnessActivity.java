package ca.mohawk.meapp1_0;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class WellnessActivity extends AppCompatActivity {

    private static String tag = "==MainActivity==";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellness);

        Log.d(tag,"onCreate()");
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        Fragment indexPage = new IndexFragment();
        fragmentTransaction.replace(R.id.frame , indexPage);
        fragmentTransaction.commit();

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        Fragment indexPage = new IndexFragment();
        fragmentTransaction.replace(R.id.frame , indexPage);
        fragmentTransaction.commit();
    }

    public void onResume(){
        super.onResume();
        Button btnRelationship = findViewById(R.id.btn_relationship);
        Button btn_conflict = findViewById(R.id.btn_conflict);
        Button btn_checkups = findViewById(R.id.btn_health);
        Button btn_personality = findViewById(R.id.btn_personality);

        btnRelationship.setOnClickListener(this::onRelationshipClick);
        btn_conflict.setOnClickListener(this::onConflictClick);
        btn_checkups.setOnClickListener(this::onCheckupClick);
        btn_personality.setOnClickListener(this::onPersonalityClick);
    }

    public void onRelationshipClick(View view){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        Fragment relationshipPage = new RelationshipFragment();
        fragmentTransaction.replace(R.id.frame, relationshipPage);
        fragmentTransaction.commit();

    }

    public void onPersonalityClick(View view){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        Fragment personlityPage= new PersonalityFragment();
        fragmentTransaction.replace(R.id.frame, personlityPage);
        fragmentTransaction.commit();

    }
    public void onCheckupClick(View view){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        Fragment checkupPage = new CheckupFragment();
        fragmentTransaction.replace(R.id.frame, checkupPage);
        fragmentTransaction.commit();

    }
    public void onConflictClick(View view){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        Fragment conflictPage = new ConflictFragment();
        fragmentTransaction.replace(R.id.frame, conflictPage);
        fragmentTransaction.commit();

    }
}