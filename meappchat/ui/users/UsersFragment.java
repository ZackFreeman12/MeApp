package ca.meapp.meappchat.ui.users;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ca.meapp.meappchat.R;
import ca.meapp.meappchat.adapter.UsersAdapter;
import ca.meapp.meappchat.databinding.FragmentUsersBinding;
import io.getstream.chat.android.client.ChatClient;
import io.getstream.chat.android.client.api.models.FilterObject;
import io.getstream.chat.android.client.api.models.QueryUsersRequest;
import io.getstream.chat.android.client.models.Filters;
import io.getstream.chat.android.client.models.User;

/**
 * create an instance of this fragment.
 */
public class UsersFragment extends Fragment {
    private FragmentUsersBinding binding;
    private UsersAdapter usersAdapter = new UsersAdapter();

    private ChatClient client = ChatClient.instance();
    public UsersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentUsersBinding.inflate(getLayoutInflater());
        setHasOptionsMenu(true);
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(binding.toolbar);

        setupToolbar();
        setupRecyclerView();
        queryAlUsers();

        return binding.getRoot();
    }


    private void setupToolbar() {
        binding.toolbar.setNavigationOnClickListener(view -> requireActivity().onBackPressed());
    }

    private void setupRecyclerView() {
        binding.usersRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.usersRecyclerView.setAdapter(usersAdapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.users_menu, menu);
        MenuItem search = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) search.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                if(query.isEmpty()){
                    queryAlUsers();
                } else {
                    searchUser(query);
                }
                return true;
            }
        });

        searchView.setOnCloseListener(() -> {
            queryAlUsers();
            return false;
        });

    }

    private void queryAlUsers(){

        QueryUsersRequest request = new QueryUsersRequest(
                Filters.ne("id", client.getCurrentUser().getId()), 0, 100);

        client.queryUsers(request).enqueue(result -> {
            if (result.isSuccess()) {
                List<User> users = result.data();
                Log.d("==UsersFragmentQ==", users.toString());
                usersAdapter.setData((ArrayList<User>) users);
            } else {
                Log.d("==UsersFragment==", result.error().getMessage().toString());
            }
        });
    }

    private void searchUser(String query){
        FilterObject filters = Filters.and(
                Filters.autocomplete("id", query),
                Filters.ne("id", client.getCurrentUser().getId()));

        QueryUsersRequest request = new QueryUsersRequest(filters, 0, 100);

        client.queryUsers(request).enqueue(result -> {
            if(result.isSuccess()){
                ArrayList<User> users = (ArrayList<User>) result.data();
                usersAdapter.setData(users);
            } else{
                Log.d("==UsersFragment==", result.error().getMessage().toString());
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}