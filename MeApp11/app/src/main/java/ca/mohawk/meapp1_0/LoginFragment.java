package ca.mohawk.meapp1_0;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import ca.mohawk.meapp1_0.databinding.FragmentLoginBinding;
import io.getstream.chat.android.client.ChatClient;

/**
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {
//    private EditText usernameEditText, passwordEditText;
//    private FragmentLoginBinding binding;
//    private final ChatClient client = ChatClient.instance();
//
   public LoginFragment() {
//        Required empty public constructor
   }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        binding = FragmentLoginBinding.inflate(getLayoutInflater());
//        if(client != null){
//            client.disconnect();
//        }
//        binding.loginButton.setOnClickListener(this::authenticateTheUser);
//
//        EditText usernameEditText = binding.usernameEditText;
//        EditText passwordEditText = binding.passwordEditText;
//        this.usernameEditText = usernameEditText;
//        this.passwordEditText = passwordEditText;
//
//        return binding.getRoot();
//    }
//
//
//    private void authenticateTheUser(View view){
//        String username = binding.usernameEditText.getText().toString();
//        String password = binding.passwordEditText.getText().toString();
//
//        if(validateInput(username, usernameEditText) && validateInput(password, passwordEditText)){
//            ChatUsers chatUser = new ChatUsers(username);
//            NavDirections action = LoginFragmentDirections.actionLoginFragmentToLandingFragment(chatUser);
//            Navigation.findNavController(view).navigate(action);
//        }
//    }
//
//    private boolean validateInput(String inputText, EditText textInputLayout){
//        if(inputText.length() <= 3){
//            //textInputLayout.setErrorEnabled(true);
//            textInputLayout.setError("Minimum 4 Characters Allowed");
//            return false;
//        } else {
//            //textInputLayout.setErrorEnabled(false);
//            textInputLayout.setError(null);
//            return true;
//        }
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }
}