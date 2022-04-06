package ca.meapp.meapp1_0.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ChatUsers implements Parcelable {
    private String username;

    public ChatUsers(String username){
        this.username = username;
    }

    protected ChatUsers(Parcel in) {
        username = in.readString();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static final Creator<ChatUsers> CREATOR = new Creator<ChatUsers>() {
        @Override
        public ChatUsers createFromParcel(Parcel in) {
            return new ChatUsers(in);
        }

        @Override
        public ChatUsers[] newArray(int size) {
            return new ChatUsers[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(username);
    }
}
