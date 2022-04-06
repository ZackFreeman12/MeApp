package ca.meapp.meapp1_0;

import android.app.Application;
import io.getstream.chat.android.client.ChatClient;
import io.getstream.chat.android.client.logger.ChatLogLevel;
import io.getstream.chat.android.livedata.ChatDomain;

public class MeApplication extends Application {

    @Override
    public void onCreate() {
            super.onCreate();
            ChatClient client = new ChatClient.Builder(this.getResources().getString(R.string.api_key), this).logLevel(ChatLogLevel.ALL).build();
            new ChatDomain.Builder(client, this).build();
        }

}
