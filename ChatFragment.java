package ca.mohawk.meapp1_0;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import com.getstream.sdk.chat.viewmodel.MessageInputViewModel;
import com.getstream.sdk.chat.viewmodel.messages.MessageListViewModel;

import ca.mohawk.meapp1_0.databinding.FragmentChatBinding;
import io.getstream.chat.android.ui.message.input.viewmodel.MessageInputViewModelBinding;
import io.getstream.chat.android.ui.message.list.header.viewmodel.MessageListHeaderViewModel;
import io.getstream.chat.android.ui.message.list.header.viewmodel.MessageListHeaderViewModelBinding;
import io.getstream.chat.android.ui.message.list.viewmodel.MessageListViewModelBinding;
import io.getstream.chat.android.ui.message.list.viewmodel.factory.MessageListViewModelFactory;

/**
 * create an instance of this fragment.
 */
public class ChatFragment extends Fragment {
    private ChatFragmentArgs args; //by NavArgs?
    private FragmentChatBinding binding;
    private String cid;

    public ChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        args = ChatFragmentArgs.fromBundle(getArguments());
        binding = FragmentChatBinding.inflate(getLayoutInflater());
        setupMessages();
        binding.messagesHeaderView.setBackButtonClickListener(() -> {
            requireActivity().onBackPressed();
        });

        return binding.getRoot();
    }

    private void setupMessages() {
        MessageListViewModelFactory factory = new MessageListViewModelFactory(cid = args.getChannelId());

        MessageListHeaderViewModel messageListHeaderViewModel = new ViewModelProvider(this, factory).get(MessageListHeaderViewModel.class);
        MessageListViewModel messageListViewModel = new ViewModelProvider(this, factory).get(MessageListViewModel.class);
        MessageInputViewModel messageInputViewModel = new ViewModelProvider(this, factory).get(MessageInputViewModel.class);

        MessageListHeaderViewModelBinding.bind(messageListHeaderViewModel, binding.messagesHeaderView, getViewLifecycleOwner());
        MessageListViewModelBinding.bind(messageListViewModel, binding.messageList, getViewLifecycleOwner());
        MessageInputViewModelBinding.bind(messageInputViewModel, binding.messageInputView, getViewLifecycleOwner());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}