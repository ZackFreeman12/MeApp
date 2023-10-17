package ca.mohawk.meapp1_0;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import ca.mohawk.meapp1_0.databinding.FragmentProfileBinding;
import io.getstream.chat.android.client.ChatClient;
import io.getstream.chat.android.client.models.User;
import io.getstream.chat.android.ui.avatar.AvatarView;


public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;
    private ProfileFragmentArgs args;
    private final ChatClient client = ChatClient.instance();
    final char[] delimiters = {' ', '_'};
    private User user;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(getLayoutInflater());
        args = ProfileFragmentArgs.fromBundle(getArguments());
        String storedEmail = args.getChatUser().getEmail();

        storedEmail = storedEmail.replace("666",".");

        DatabaseHelper dbh = new DatabaseHelper(binding.getRoot().getContext());

        boolean success = dbh.getProfileData(storedEmail) != null;
        if (success) {
            setupUser();
            String[] profileStrings = dbh.getProfileData(storedEmail);

            String name = toTitleCase(profileStrings[0]);
            String datePref = toTitleCase(profileStrings[4]);
            String gender = toTitleCase(profileStrings[3]);

            binding.tvAge.setText(profileStrings[1]);
            binding.tvPref.setText(datePref);
            binding.tvAnswer.setText(name + " said: " + profileStrings[5]);
            binding.tvName.setText(name);
            binding.tvEmail.setText("Signed in as " + profileStrings[2]);
            User currentUser = client.getCurrentUser();
            AvatarView avatar = binding.avatarView2.findViewById(R.id.avatarView2);
            avatar.setUserData(currentUser);
            binding.themButton3.setOnClickListener(this::themButton);
            binding.homeButton3.setOnClickListener(this::homeButton);


        }
        else {
            Toast.makeText(binding.getRoot().getContext(), "No data found", Toast.LENGTH_SHORT).show();
        }
        //storedEmail = storedEmail.replace(".","$");
        return binding.getRoot();
    }

    public void themButton(View view){
        args = ProfileFragmentArgs.fromBundle(getArguments());
        ChatUsers chatUser = args.getChatUser();
        NavDirections action = ProfileFragmentDirections.actionProfileFragmentToChannelFragment(chatUser);
        Navigation.findNavController(view).navigate(action);
        //binding.themButton.setTextColor(binding.themButton.getContext().getResources().getColor(R.color.salmon, binding.themButton.getContext().getTheme()));
    }

    public void homeButton(View view){
        ChatUsers chatUser = args.getChatUser();
        NavDirections action = ProfileFragmentDirections.actionProfileFragmentToLandingFragment(chatUser);
        Navigation.findNavController(view).navigate(action);
        //binding.themButton.setTextColor(binding.themButton.getContext().getResources().getColor(R.color.salmon, binding.themButton.getContext().getTheme()));
    }

    private void setupUser() {
        args = ProfileFragmentArgs.fromBundle(getArguments());
        if(client.getCurrentUser() == null){

            user = new User();
            user.setId(args.getChatUser().getEmail());
            user.setName(args.getChatUser().getName());
            Log.d("TAG",user.getId());
        }
        else{

            user = client.getCurrentUser();
            Log.d("TAG",user.toString());
        }

        String token = client.devToken(user.getId());
        client.connectUser(user, token).enqueue();
    }

    private static String toTitleCase(String str) {

        if(str == null || str.isEmpty())
            return "";

        if(str.length() == 1)
            return str.toUpperCase();

        //split the string by space
        String[] parts = str.split(" ");

        StringBuilder sb = new StringBuilder( str.length() );

        for(String part : parts){

            if(part.length() > 1 )
                sb.append( part.substring(0, 1).toUpperCase() )
                        .append( part.substring(1).toLowerCase() );
            else
                sb.append(part.toUpperCase());

            sb.append(" ");
        }

        return sb.toString().trim();
    }

}