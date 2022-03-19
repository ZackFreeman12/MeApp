package ca.meapp.meappchat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;

import io.getstream.chat.android.client.ChatClient;
import io.getstream.chat.android.client.logger.ChatLogLevel;
import io.getstream.chat.android.client.models.User;
import io.getstream.chat.android.livedata.ChatDomain;

public class MainActivity extends AppCompatActivity {
    private NavController navController;
    //private ChatClient client = ChatClient.instance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ChatClient client = new ChatClient.Builder(
                getResources().getString(R.string.api_key), this).logLevel(ChatLogLevel.ALL).build();
        new ChatDomain.Builder(client, this).build();
        navController = Navigation.findNavController(this, R.id.navHostFragment);

        if(navController.getCurrentDestination().getLabel().toString().contains("login")){
            User currentUser = client.getCurrentUser();
            if(currentUser != null){
                User user = new User();
                user.setId(currentUser.getId());
                user.setName(currentUser.getName());

                String token = client.devToken(user.getId());
                client.connectUser(user,token).enqueue();
                navController.navigate(R.id.navigateLoginFragmentToChannelFragment);
            }
        }
    }


}