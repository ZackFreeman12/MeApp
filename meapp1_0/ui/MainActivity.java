package ca.meapp.meapp1_0.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import java.util.Objects;
import ca.meapp.meapp1_0.R;
import ca.meapp.meapp1_0.model.ChatUsers;
import ca.meapp.meapp1_0.ui.login.LoginFragmentDirections;
import io.getstream.chat.android.client.ChatClient;
import io.getstream.chat.android.client.models.User;

public class MainActivity extends AppCompatActivity {
    private final ChatClient client = ChatClient.instance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavController navController = Navigation.findNavController(this, R.id.navHostFragment);

        if(Objects.requireNonNull(Objects.requireNonNull(navController.getCurrentDestination()).getLabel()).toString().contains("login")){
            User currentUser = client.getCurrentUser();
            if(currentUser != null){
                ChatUsers chatUser = new ChatUsers(currentUser.getName());
                LoginFragmentDirections.ActionLoginFragmentToLandingFragment action = LoginFragmentDirections.actionLoginFragmentToLandingFragment(chatUser);
                navController.navigate(action);
            }
        }
    }


}