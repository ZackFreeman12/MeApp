package ca.mohawk.meapp1_0;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class ConflictFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragView = inflater.inflate(R.layout.fragment_conflict, container, false);
        Button btnSkip = fragView.findViewById(R.id.button3);
        btnSkip.setOnClickListener(this::sexWellnessButton);


        return fragView;

    }


    public void sexWellnessButton(View view) {
        //Navigate to user stuff with chatUser arguments
        ConflictFragmentArgs args = ConflictFragmentArgs.fromBundle(getArguments());
        ChatUsers chatUser = args.getChatUser();
        NavDirections action = ConflictFragmentDirections.actionConflictFragmentToRelationshipFragment2(chatUser);
        Navigation.findNavController(view).navigate(action);
    }
}