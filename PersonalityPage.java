package ca.mohawk.meapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class PersonalityPage extends Fragment {

    private String list[] =
            {"Openness", "Extraversion", "Neuroticism", "Agreeableness", "Influential", "INTJ"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragView =  inflater.inflate(R.layout.fragment_personality_page, container, false);

        TextView pageHeader = fragView.findViewById(R.id.textView2);
        pageHeader.setText("Your personality type is: "+list[random()]);

        return fragView;
    }

    public Integer random () {
        int random = (int) (Math.random() * 5);
        return random;
    }
}