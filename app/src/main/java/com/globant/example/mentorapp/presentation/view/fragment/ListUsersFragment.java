package com.globant.example.mentorapp.presentation.view.fragment;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.globant.example.mentorapp.MentorApplication;
import com.globant.example.mentorapp.R;
import com.globant.example.mentorapp.data.entity.UserEntity;
import com.globant.example.mentorapp.domain.interactor.ListUsersInteractorImpl;
import com.globant.example.mentorapp.presentation.model.SharedUserViewModel;
import com.globant.example.mentorapp.presentation.presenter.ListUsersPresenterImpl;
import com.globant.example.mentorapp.presentation.view.activity.HomeScreenActivity;
import com.globant.example.mentorapp.presentation.view.adapter.ListUsersAdapter;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;

/**
 * A placeholder fragment containing a simple view.
 */
public class ListUsersFragment extends LifecycleFragment implements ListUsersInterface {

    protected RecyclerView mRecyclerView;

    public SharedUserViewModel model;
    @Inject
    protected LinearLayoutManager mLayoutManager;
    @Inject
    protected Retrofit retrofitClient;
    @Inject
    protected ListUsersInteractorImpl userInteractor;
    protected ListUsersAdapter listUsersAdapter;
    private ListUsersPresenterImpl presenter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ListUsersPresenterImpl(this);
        ((MentorApplication) getActivity().getApplicationContext()).getUserComponent().inject(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_screen, container, false);
        model = ViewModelProviders.of(this).get(SharedUserViewModel.class);
        if (model.getUsers() == null || model.getUsers().getValue() == null) {
            presenter.getUsersFromService(retrofitClient, userInteractor);
            subscribe();

        } else {
            listUsersAdapter = new ListUsersAdapter(model.getUsers().getValue(), getContext());
        }
        mRecyclerView = (RecyclerView) view.findViewById(R.id.list_item_container);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(
                new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(listUsersAdapter);
        return view;
    }


    @Override
    public void UsersReady(List<UserEntity> allUsers) {
        ((HomeScreenActivity) getContext()).simpleSnackbarMessage("Users Count:" + allUsers.size());
        model.setUsers(allUsers);
        listUsersAdapter = new ListUsersAdapter(allUsers, getContext());
        listUsersAdapter.notifyDataSetChanged();

    }

    @Override
    public void UsersError(String message) {
        ((HomeScreenActivity) getActivity()).simpleSnackbarMessage(message);

    }

    private void subscribe() {
        final Observer<List<UserEntity>> usersObserver = new Observer<List<UserEntity>>() {
            @Override
            public void onChanged(@Nullable List<UserEntity> userEntityList) {
                listUsersAdapter = new ListUsersAdapter(userEntityList, getContext());
                mRecyclerView.setAdapter(listUsersAdapter);
                listUsersAdapter.notifyDataSetChanged();
            }
        };
        model.getUsers().observe(this, usersObserver);
    }


}
