package ca.meapp.meapp1_0.ui.landing;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ca.meapp.meapp1_0.databinding.FragmentLandingBinding;
import ca.meapp.meapp1_0.model.ChatUsers;

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
        args = LandingFragmentArgs.fromBundle(getArguments());
        binding.themButton.setOnClickListener(this::themButton);

        return binding.getRoot();
    }

    public void themButton(View view){
        ChatUsers chatUser = args.getChatUser();
        NavDirections action = LandingFragmentDirections.actionLandingFragmentToChannelFragment(chatUser);
        Navigation.findNavController(view).navigate(action);
        //binding.themButton.setTextColor(binding.themButton.getContext().getResources().getColor(R.color.salmon, binding.themButton.getContext().getTheme()));
    }

    public void youButton(View view){
        //Navigate to user stuff with chatUser arguments
    }
}