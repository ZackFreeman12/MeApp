package ca.mohawk.meapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class RelationshipContent extends Fragment {

    private static String tag = "==RelationshipFragment";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(tag, "onCreateView()");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_relationship_content, container, false);

    }
}