package com.globant.example.mentorapp.home.presentation.view.fragment;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.globant.example.mentorapp.MentorApplication;
import com.globant.example.mentorapp.R;
import com.globant.example.mentorapp.data.livedata.SharedViewModel;
import com.globant.example.mentorapp.home.presentation.model.ListUsersViewModel;
import com.globant.example.mentorapp.home.presentation.model.ModelUserEntity;
import com.globant.example.mentorapp.home.presentation.presenter.ListUsersPresenterImpl;
import com.globant.example.mentorapp.home.presentation.view.adapter.ListUsersAdapter;
import com.globant.example.mentorapp.mvp.base.BaseActivity;
import com.globant.example.mentorapp.mvp.base.BaseRecyclerViewAdapter;
import com.globant.example.mentorapp.mvp.base.BaseView;
import com.globant.example.mentorapp.subscriberDetails.presentation.view.fragment.UserDetailsFragment;

import java.util.List;

import javax.inject.Inject;

/**
 * A placeholder fragment containing a list of Users with his name.
 */
public class ListUsersViewFragment extends LifecycleFragment implements BaseView<ListUsersViewModel>, BaseRecyclerViewAdapter.onUserClick {

    public static final String LIST_TAG = "listUserFragment";
    @Inject
    public ListUsersPresenterImpl presenter;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private SharedViewModel model;
    private ListUsersAdapter listUsersAdapter;
    private RecyclerView listUsersRecyclerView;
    private BaseActivity parent;

    public static ListUsersViewFragment getInstance() {
        return new ListUsersViewFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MentorApplication) getActivity().getApplicationContext()).getApplicationComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_screen, container, false);
        model = ViewModelProviders.of(this).get(SharedViewModel.class);
        parent = (BaseActivity) getActivity();
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(
                getResources().getInteger(R.integer.number_of_users_columns), LinearLayoutManager.VERTICAL);
        if (model.getUsers() != null || model.getUsers().getValue() != null) {
            listUsersAdapter = new ListUsersAdapter(model.getUsers().getValue(), this);
            parent.hideProgress();
        }
        listUsersRecyclerView = (RecyclerView) view.findViewById(R.id.list_item_container);
        defineRecyclerView(listUsersRecyclerView, listUsersAdapter);
        return view;
    }

    @Override
    public void render(ListUsersViewModel usersModel) {
        if (usersModel.getUsers() != null) {
            model.setUsers(usersModel.getUsers());
            usersReady();
        } else if (usersModel.getError() != null) {
            switch (usersModel.getError()) {
                case ERROR_CONNECTION:
                    usersErrorConnectivity();
                    break;
                case ERROR_RESPONSE:
                    usersErrorHttp();
                    break;
            }
        }

        if (usersModel.getProgress()) {
            parent.showProgress();
        } else {
            parent.hideProgress();
        }
    }

    private void usersReady() {
        listUsersAdapter = new ListUsersAdapter(model.getUsers().getValue(), this);
        listUsersRecyclerView.setAdapter(listUsersAdapter);
        listUsersAdapter.notifyDataSetChanged();
    }

    private void usersErrorHttp() {
        parent.simpleSnackBarMessage(getString(R.string.http_error_message));
    }

    private void usersErrorConnectivity() {
        parent.simpleSnackBarMessage(getString(R.string.communication_error_message));
    }

    private void subscribe() {
        final Observer<List<ModelUserEntity>> usersObserver = new Observer<List<ModelUserEntity>>() {
            @Override
            public void onChanged(@Nullable List<ModelUserEntity> userEntityList) {
                listUsersAdapter = new ListUsersAdapter(userEntityList, ListUsersViewFragment.this);
                defineRecyclerView(listUsersRecyclerView, listUsersAdapter);
                listUsersAdapter.notifyDataSetChanged();
            }
        };
        model.getUsers().observe(this, usersObserver);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.registerBus();
        presenter.attachView(this);
        if (model.getUsers().getValue() == null) {
            presenter.getUsersList();
            subscribe();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.unregisterBus();
        presenter.detachView();
    }

    private void defineRecyclerView(RecyclerView listUsersRecyclerView, RecyclerView.Adapter adapter) {
        listUsersRecyclerView.setHasFixedSize(true);
        listUsersRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        listUsersRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onUserSelected(String userId) {
        UserDetailsFragment detailsFragment = UserDetailsFragment.getInstance();
        Bundle bundle = new Bundle();
        bundle.putString(getString(R.string.bundle_selected_user_id), userId);
        detailsFragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, detailsFragment)
                .addToBackStack(null)
                .commit();
    }
}
