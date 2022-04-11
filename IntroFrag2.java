package ca.mohawk.meapp1_0;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class IntroFrag2 extends Fragment {
    Button btnCasual;
    Button btnHookup;
    Button btnRomance;
    String email;

    public IntroFrag2(String email) {
        this.email = email;
    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragView = inflater.inflate(R.layout.fragment_intro_frag2,
                container, false);
        btnCasual = fragView.findViewById(R.id.btnCasual);
        btnHookup = fragView.findViewById(R.id.btnHookup);
        btnRomance = fragView.findViewById(R.id.btnRomance);

        btnCasual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseHelper dbh = new DatabaseHelper(getActivity());

                dbh.updateProfile(email,null,null,"Casual Dates",false,"Not Answered Yet");
                Toast.makeText(getActivity(), "Casual Chosen", Toast.LENGTH_SHORT).show();
            }
        });
        btnHookup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseHelper dbh = new DatabaseHelper(getActivity());

                dbh.updateProfile(email,null,null,"Hookups",false,"Not Answered Yet");
                Toast.makeText(getActivity(), "Hookups Chosen", Toast.LENGTH_SHORT).show();
            }
        });
        btnRomance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseHelper dbh = new DatabaseHelper(getActivity());

                dbh.updateProfile(email,null,null,"Romantic Dates",false,"Not Answered Yet");
                Toast.makeText(getActivity(), "Romance Chosen", Toast.LENGTH_SHORT).show();
            }
        });
        return fragView;

    }
}