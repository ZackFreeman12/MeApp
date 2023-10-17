package ca.mohawk.meapp1_0;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class IndexFragment extends Fragment {

    Button btn_relationship;
    Button btn_conflict;
    Button btn_checkups;
    Button btn_personality;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragView =  inflater.inflate(R.layout.fragment_index, container, false);

        btn_relationship = fragView.findViewById(R.id.btn_relationship);
        btn_conflict = fragView.findViewById(R.id.btn_conflict);
        btn_checkups = fragView.findViewById(R.id.btn_health);
        btn_personality = fragView.findViewById(R.id.btn_personality);

        //btn_relationship.setOnClickListener(this::onRelationshipClick);


        return fragView;

    }
}