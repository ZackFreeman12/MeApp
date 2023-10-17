package ca.mohawk.meapp1_0;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.ViewGroup;

import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import ca.mohawk.meapp1_0.databinding.UserRowLayoutBinding;
import io.getstream.chat.android.client.ChatClient;
import io.getstream.chat.android.client.models.User;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyViewHolder>{
    private ChatClient client = ChatClient.instance();
    private ArrayList<User> userList = new ArrayList<>();


    class MyViewHolder extends RecyclerView.ViewHolder {
        private UserRowLayoutBinding binding;
        public MyViewHolder(UserRowLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(
                UserRowLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false)
        );
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Date date = new Date();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String stringDate = formatter.format(date);
        User currentUser = userList.get(position);
        holder.binding.avatarImageView.setUserData(currentUser);
        holder.binding.usernameTextView.setText(currentUser.getName());
        holder.binding.lastActiveTextView.setText(stringDate);
        holder.binding.rootLayout.setOnClickListener(view -> UsersAdapter.this.createNewChannel(currentUser.getId(), holder));
    }

    @SuppressLint("CheckResult")
    private void createNewChannel(String selectedUser, MyViewHolder holder) {
        ArrayList<String> members = new ArrayList<>();
        members.add(client.getCurrentUser().getId());
        members.add(selectedUser);
        client.createChannel("messaging", members).enqueue(result -> {
            if(result.isSuccess()){
                navigateToChatFragment(holder, result.data().getCid());
            } else{
                Log.d("==UsersAdapter", result.error().getMessage().toString());
            }
        });
    }

    private void navigateToChatFragment(MyViewHolder holder, String cid) {
        NavDirections action = UserFragmentDirections.actionUsersFragmentToChatFragment(cid);
        Navigation.findNavController(holder.itemView).navigate(action);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(ArrayList<User> newList){
        userList = newList;
        notifyDataSetChanged();
    }
}
