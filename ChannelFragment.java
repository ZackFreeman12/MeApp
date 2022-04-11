package ca.mohawk.meapp1_0;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Collections;
import java.util.Objects;

import ca.mohawk.meapp1_0.databinding.FragmentChannelBinding;
import io.getstream.chat.android.client.ChatClient;
import io.getstream.chat.android.client.api.models.FilterObject;
import io.getstream.chat.android.client.models.Channel;
import io.getstream.chat.android.client.models.Filters;
import io.getstream.chat.android.client.models.User;
import io.getstream.chat.android.offline.ChatDomain;
import io.getstream.chat.android.ui.avatar.AvatarView;
import io.getstream.chat.android.ui.channel.list.header.viewmodel.ChannelListHeaderViewModel;
import io.getstream.chat.android.ui.channel.list.header.viewmodel.ChannelListHeaderViewModelBinding;
import io.getstream.chat.android.ui.channel.list.viewmodel.ChannelListViewModel;
import io.getstream.chat.android.ui.channel.list.viewmodel.ChannelListViewModelBinding;
import io.getstream.chat.android.ui.channel.list.viewmodel.factory.ChannelListViewModelFactory;


/**
 * create an instance of this fragment.
 */
public class ChannelFragment extends Fragment {

    private ChannelFragmentArgs args;
    private User user;
    private final ChatClient client = ChatClient.instance();
    private FragmentChannelBinding binding;

    public ChannelFragment() {
        // Required empty public constructor
    }

    @SuppressLint("WrongConstant")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentChannelBinding.inflate(getLayoutInflater());
        args = ChannelFragmentArgs.fromBundle(getArguments());
        setupUser();
        setupChannels();
        setupDrawer();

        binding.homeButton2.setOnClickListener(this::homeButton);
        binding.youButton2.setOnClickListener(this::youButton);
        binding.channelsView.setChannelDeleteClickListener(this::deleteChannel);
        binding.channelListHeaderView.setOnlineTitle("Matched Chat!");
        binding.channelListHeaderView.setOnActionButtonClickListener(() -> Navigation.findNavController(binding.getRoot()).navigate(R.id.action_channelFragment_to_usersFragment));
        binding.channelsView.setChannelItemClickListener(channel -> {
            NavDirections action = ChannelFragmentDirections.actionChannelFragmentToChatFragment(channel.getCid());
            Log.d("==ChannelFrag", channel.getCid());
            Navigation.findNavController(Objects.requireNonNull(this.getView())).navigate(action);
        });
        binding.channelListHeaderView.setOnUserAvatarClickListener(() -> binding.drawerLayout.openDrawer(Gravity.START));

        return binding.getRoot();
    }

    public void homeButton(View view){
        ChatUsers chatUser = args.getChatUser();
        NavDirections action = ChannelFragmentDirections.actionChannelFragmentToLandingFragment(chatUser);
        Navigation.findNavController(view).navigate(action);
        //binding.themButton.setTextColor(binding.themButton.getContext().getResources().getColor(R.color.salmon, binding.themButton.getContext().getTheme()));
    }

    private void setupDrawer() {
        binding.navigationView.setNavigationItemSelectedListener(menuItem -> {
            if(menuItem.getItemId() == R.id.logout_menu){
                logout();
            }
            return false;
        });
        User currentUser = client.getCurrentUser();
        View headerView = binding.navigationView.getHeaderView(0);
        AvatarView headerAvatar = headerView.findViewById(R.id.avatarView);
        //TextView headerId = headerView.findViewById(R.id.id_textView);
        // headerName
        TextView headerId = headerView.findViewById(R.id.id_textView);
        if(currentUser != null) {
            headerAvatar.setUserData(currentUser);
            headerId.setText(currentUser.getName());
            //headerName.setText(currentUser.getName());
        }
    }

    private void logout(){
       AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setMessage("Are you sure you want to log out?");
        builder.setPositiveButton("YES", (dialogInterface, i) -> {
            client.disconnect();
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_channelFragment_to_loginActivity);
            Toast.makeText(getContext(), "Logged Out Successfully!", Toast.LENGTH_SHORT).show();
        });
        builder.setNegativeButton("NO", (dialogInterface, i) -> {
            //Do Nothing, simply exits the AlertDialogBox
        });
        builder.create().show();
    }

    private void deleteChannel(Channel channel){
        ChatDomain.instance().deleteChannel(channel.getCid()).enqueue(result -> {
            if(result.isSuccess()){
                Toast.makeText(requireContext(), "Chatroom Delete!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupChannels() {
        FilterObject filters = Filters.and(
                Filters.eq("type", "messaging"),
                Filters.in("members", Collections.singletonList(Objects.requireNonNull(client.getCurrentUser()).getId()))
        );

        ChannelListViewModelFactory viewModelFactory = new ChannelListViewModelFactory(
                filters,
                ChannelListViewModel.DEFAULT_SORT
        );

        ChannelListViewModel channelListViewModel = new ViewModelProvider(this, viewModelFactory).get(ChannelListViewModel.class);
        ChannelListHeaderViewModel channelListHeaderViewModel = new ViewModelProvider(this).get(ChannelListHeaderViewModel.class);

        ChannelListHeaderViewModelBinding.bind(channelListHeaderViewModel, binding.channelListHeaderView, getViewLifecycleOwner());
        ChannelListViewModelBinding.bind(channelListViewModel, binding.channelsView, getViewLifecycleOwner());

    }

    private void setupUser() {
        if(client.getCurrentUser() == null){

            user = new User();
            user.setId(args.getChatUser().getEmail());
            user.setName(args.getChatUser().getName());
            Log.d("TAG",user.getId());
        }
        else{

            user = client.getCurrentUser();
            Log.d("TAG",user.toString());
        }

        String token = client.devToken(user.getId());
        client.connectUser(user, token).enqueue();
    }

    public void youButton(View view){
        //Navigate to user stuff with chatUser arguments
        args = ChannelFragmentArgs.fromBundle(getArguments());
        ChatUsers chatUser = args.getChatUser();
        NavDirections action = ChannelFragmentDirections.actionChannelFragmentToProfileFragment(chatUser);
        Navigation.findNavController(view).navigate(action);

    }
}