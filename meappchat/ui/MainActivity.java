package ca.meapp.meappchat.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.util.Log;

import ca.meapp.meappchat.R;
import ca.meapp.meappchat.model.ChatUsers;
import ca.meapp.meappchat.ui.login.LoginFragmentDirections;
import io.getstream.chat.android.client.ChatClient;
import io.getstream.chat.android.client.models.User;

public class MainActivity extends AppCompatActivity {
    private NavController navController;
    private ChatClient client = ChatClient.instance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navController = Navigation.findNavController(this, R.id.navHostFragment);

        if(navController.getCurrentDestination().getLabel().toString().contains("login")){
            User currentUser = client.getCurrentUser();
            if(currentUser != null){
                ChatUsers chatUser = new ChatUsers(currentUser.getName());
                LoginFragmentDirections.NavigateLoginFragmentToChannelFragment action = LoginFragmentDirections.navigateLoginFragmentToChannelFragment(chatUser);

                navController.navigate(action);
            }
        }
    }


}