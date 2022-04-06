package ca.mohawk.meapp1_0;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class IntroActivity extends AppCompatActivity {


    Button btnNext;
    public int page = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        btnNext = findViewById(R.id.btnNext);
        page = 1;
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        Fragment myFragment1 = new IntroFrag1();
       fragmentTransaction.replace(R.id.fragContainer, myFragment1);
        fragmentTransaction.commit();



        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page += 1;
                if (page == 2){
                    Log.d("tag", "GOOOOO");
                    btnNext.setText("Next");
                    FragmentTransaction fragmentTransaction2 = fm.beginTransaction();
                    Fragment myFragment2 = new IntroFrag2();
                    fragmentTransaction2.replace(R.id.fragContainer, myFragment2);
                    fragmentTransaction2.commit();
                }
                else {
                    FragmentTransaction fragmentTransaction3 = fm.beginTransaction();
                    Fragment myFragment3 = new IntroFrag3();
                    fragmentTransaction3.replace(R.id.fragContainer, myFragment3);
                    fragmentTransaction3.commit();
                }

            }
        });
    }




}