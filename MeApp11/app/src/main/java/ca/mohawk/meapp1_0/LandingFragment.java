package ca.mohawk.meapp1_0;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ca.mohawk.meapp1_0.databinding.FragmentLandingBinding;
import io.getstream.chat.android.client.ChatClient;
import io.getstream.chat.android.client.models.User;

/**
 * create an instance of this fragment.
 */
public class LandingFragment extends Fragment {
    private FragmentLandingBinding binding;
    private LandingFragmentArgs args;



    public LandingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentLandingBinding.inflate(getLayoutInflater());

        binding.themButton.setOnClickListener(this::themButton);
        binding.youButton.setOnClickListener(this::youButton);
        binding.btnDaily.setOnClickListener(this::dailyButton);
        binding.btnSex.setOnClickListener(this::sexWellnessButton);
        binding.btnQuestionare.setOnClickListener(this::QaButton);

        return binding.getRoot();
    }

    public void themButton(View view){
        args = LandingFragmentArgs.fromBundle(getArguments());
        ChatUsers chatUser = args.getChatUser();
        NavDirections action = LandingFragmentDirections.actionLandingFragmentToChannelFragment(chatUser);
        Navigation.findNavController(view).navigate(action);
        //binding.themButton.setTextColor(binding.themButton.getContext().getResources().getColor(R.color.salmon, binding.themButton.getContext().getTheme()));
    }

    public void youButton(View view){
        //Navigate to user stuff with chatUser arguments
        args = LandingFragmentArgs.fromBundle(getArguments());
        ChatUsers chatUser = args.getChatUser();
        NavDirections action = LandingFragmentDirections.actionLandingFragmentToProfileFragment(chatUser);
        Navigation.findNavController(view).navigate(action);

    }

    public void dailyButton(View view){
        //Navigate to user stuff with chatUser arguments
        MainActivity mainActivity = new MainActivity();

        Intent intent = new Intent(getActivity(), QuestionActivity.class);
        intent.putExtra("storedEmail",mainActivity.getEmail());
        startActivity(intent);
    }

    public void sexWellnessButton(View view){
        //Navigate to user stuff with chatUser arguments
        args = LandingFragmentArgs.fromBundle(getArguments());
        ChatUsers chatUser = args.getChatUser();
        NavDirections action = LandingFragmentDirections.actionLandingFragmentToCheckupFragment(chatUser);
        Navigation.findNavController(view).navigate(action);
    }

    public void QaButton(View view){
        //Navigate to user stuff with chatUser arguments
        MainActivity mainActivity = new MainActivity();

        Intent intent = new Intent(getActivity(), QuestionnaireActivity.class);
        intent.putExtra("storedEmail",mainActivity.getEmail());
        startActivity(intent);
    }


}