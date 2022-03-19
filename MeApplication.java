package ca.meapp.meappchat;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.Nullable;

import io.getstream.chat.android.client.ChatClient;
import io.getstream.chat.android.client.logger.ChatLogLevel;
import io.getstream.chat.android.livedata.ChatDomain;

public class MeApplication extends Application {

    @Override
    public void onCreate() {
            super.onCreate();

            ChatClient client = new ChatClient.Builder(
            getResources().getString(R.string.api_key), this).logLevel(ChatLogLevel.ALL).build();
            new ChatDomain.Builder(client, this).build();

        }

}
