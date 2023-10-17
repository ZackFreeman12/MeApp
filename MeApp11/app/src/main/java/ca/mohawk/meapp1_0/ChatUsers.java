package ca.mohawk.meapp1_0;

import android.os.Parcel;
import android.os.Parcelable;

public class ChatUsers implements Parcelable {
    private String email;
    private String name;

    public ChatUsers(String email,String name){
        this.email = email;
        this.name = name;
    }

    protected ChatUsers(Parcel in) {
        email = in.readString();
        name = in.readString();

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        parcel.writeString(email);
        parcel.writeString(name);
    }
}
