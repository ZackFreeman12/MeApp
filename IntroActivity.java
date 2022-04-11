package ca.mohawk.meapp1_0;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class IntroActivity extends AppCompatActivity {


    Button btnNext;
    Button btnSkip;
    Intent intent;
    public int page = 1;
    String storedEmail;
    String storedName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        btnNext = findViewById(R.id.btnNext);
        btnSkip = findViewById(R.id.btnSkip);

        page = 1;
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        Fragment myFragment1 = new IntroFrag1();
       fragmentTransaction.replace(R.id.fragContainer, myFragment1);
        fragmentTransaction.commit();

        intent = getIntent();

        storedEmail = intent.getStringExtra("storedEmail");
        storedName = intent.getStringExtra(("storedName"));




        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page += 1;
                if (page == 2){
                    Log.d("tag", "GOOOOO");
                    btnNext.setText("Next");
                    FragmentTransaction fragmentTransaction2 = fm.beginTransaction();
                    Fragment myFragment2 = new IntroFrag2(storedEmail);
                    fragmentTransaction2.replace(R.id.fragContainer, myFragment2);
                    fragmentTransaction2.commit();
                }
                else if(page == 3){
                    FragmentTransaction fragmentTransaction3 = fm.beginTransaction();
                    Fragment myFragment3 = new IntroFrag3();
                    fragmentTransaction3.replace(R.id.fragContainer, myFragment3);
                    fragmentTransaction3.commit();
                }
                else{
                    goToMain();
                }

            }
        });

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper dbh = new DatabaseHelper(IntroActivity.this);

                dbh.updateProfile(storedEmail,null,null,"Casual Dates",false,"Not Answered Yet");
                goToMain();

            }
        });
    }

    public void goToMain(){


        ChatUsers chatUser = new ChatUsers(storedEmail,storedName);
        Intent intent1 = new Intent(this, MainActivity.class);
        intent1.putExtra("storedEmail",storedEmail);
        intent1.putExtra("storedName",storedName);
        intent1.putExtra("chatUser",chatUser);
        startActivity(intent1);
    }




}