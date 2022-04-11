package ca.mohawk.meapp1_0;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.exoplayer2.C;

import java.util.Objects;
import io.getstream.chat.android.client.ChatClient;
import io.getstream.chat.android.client.models.User;

public class MainActivity extends AppCompatActivity {
    private final ChatClient client = ChatClient.instance();
    String storedEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      NavController navController = Navigation.findNavController(this, R.id.navHostFragment);

        if(Objects.requireNonNull(Objects.requireNonNull(navController.getCurrentDestination()).getLabel()).toString().contains("landing")){

            //User currentUser = client.getCurrentUser();

           // if(currentUser != null){
                Log.d("TAG","HELLO");
                Intent intent = getIntent();

                storedEmail = intent.getStringExtra("storedEmail");
                String storedName = intent.getStringExtra(("storedName"));
                //will replace all . with @
            if(storedEmail != null){
                storedEmail = storedEmail.replace(".","666");
                ChatUsers chatUser = new ChatUsers(storedEmail,storedName);
                LandingFragmentDirections.actionLandingFragmentSelf(chatUser);
                NavDirections action = LandingFragmentDirections.actionLandingFragmentSelf(chatUser);
                navController.navigate(action);
            }

           // }


        }
    }

    public String getEmail(){
        return storedEmail;
    }
}
