package ca.meapp.meappchat;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import io.getstream.chat.android.client.models.User;

/**
 * create an instance of this fragment.
 */
public class loginFragment extends Fragment {
    private EditText usernameEditText, passwordEditText;
    public loginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        TextView login = view.findViewById(R.id.loginButton);
        EditText usernameEditText = view.findViewById(R.id.usernameEditText);
        EditText passwordEditText = view.findViewById(R.id.passwordEditText);
        this.usernameEditText = usernameEditText;
        this.passwordEditText = usernameEditText;
        System.out.println(view.getId());
        login.setOnClickListener(this::authenticateTheUser);
        return view;
    }

    public void login(View view){
        Navigation.findNavController(view).navigate(R.id.navigateLoginFragmentToChannelFragment);
    }

    private void authenticateTheUser(View view){

        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        System.out.println(username.length());
        if(validateInput(username, usernameEditText) && validateInput(password, passwordEditText)){
            User user = new User();
            user.setName(username);
            //login(view);
        }
    }

    private boolean validateInput(String inputText, EditText textInputLayout){
        if(inputText.length() <= 3){
            //textInputLayout.setErrorEnabled(true);
            textInputLayout.setError("Minimum 4 Characters Allowed");
            return false;
        } else {
            //textInputLayout.setErrorEnabled(false);
            textInputLayout.setError(null);
            return true;
        }
    }
}