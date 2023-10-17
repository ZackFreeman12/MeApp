package ca.mohawk.meapp1_0;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class RelationshipFragment extends Fragment {

    private static String tag = "==RelationshipFragment";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(tag, "onCreateView()");
        View fragView = inflater.inflate(R.layout.fragment_relationship, container, false);
        Button btnSkip = fragView.findViewById(R.id.button3);
        btnSkip.setOnClickListener(this::sexWellnessButton);


        return fragView;

    }


    public void sexWellnessButton(View view) {
        //Navigate to user stuff with chatUser arguments
        RelationshipFragmentArgs args = RelationshipFragmentArgs.fromBundle(getArguments());
        ChatUsers chatUser = args.getChatUser();
        NavDirections action = RelationshipFragmentDirections.actionRelationshipFragment2ToPersonalityFragment(chatUser);
        Navigation.findNavController(view).navigate(action);
    }
}