package ca.mohawk.meapp1_0;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;


public class PersonalityFragment extends Fragment {

    private String list[] =
            {"Openness", "Extraversion", "Neuroticism", "Agreeableness", "Influential", "INTJ"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragView =  inflater.inflate(R.layout.fragment_personality, container, false);

        TextView pageHeader = fragView.findViewById(R.id.textView2);
        pageHeader.setText("Your personality type is: "+list[random()]);
        Button btnSkip = fragView.findViewById(R.id.button3);
        btnSkip.setOnClickListener(this::sexWellnessButton);

        return fragView;
    }

    public Integer random () {
        int random = (int) (Math.random() * 5);
        return random;
    }

    public void sexWellnessButton(View view) {
        //Navigate to user stuff with chatUser arguments
        PersonalityFragmentArgs args = PersonalityFragmentArgs.fromBundle(getArguments());
        ChatUsers chatUser = args.getChatUser();
        NavDirections action = PersonalityFragmentDirections.actionPersonalityFragmentToLandingFragment(chatUser);
        Navigation.findNavController(view).navigate(action);
    }
}