package com.globant.example.mentorapp.home.presentation.view.fragment;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import com.globant.example.mentorapp.MentorApplication;
import com.globant.example.mentorapp.R;
import com.globant.example.mentorapp.home.presentation.model.ModelUserEntity;
import com.globant.example.mentorapp.home.presentation.model.SharedUserViewModel;
import com.globant.example.mentorapp.home.presentation.model.UsersModel;
import com.globant.example.mentorapp.home.presentation.presenter.ListUsersPresenter;
import com.globant.example.mentorapp.home.presentation.view.activity.HomeScreenActivity;
import com.globant.example.mentorapp.home.presentation.view.adapter.ListUsersAdapter;

import java.util.List;

import javax.inject.Inject;

/**
 * A placeholder fragment containing a simple view.
 */
public class ListUsersFragment extends LifecycleFragment implements ListUsersInterface {

    public SharedUserViewModel model;
    @Inject
    public ListUsersPresenter presenter;
    @Inject
    protected LinearLayoutManager linearLayoutManager;
    protected ListUsersAdapter listUsersAdapter;
    @Inject
    DividerItemDecoration itemDecoration;
    private RecyclerView listUsersRecyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MentorApplication) getActivity().getApplicationContext()).getApplicationComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_screen, container, false);
        model = ViewModelProviders.of(this).get(SharedUserViewModel.class);
        if (model.getUsers() == null || model.getUsers().getValue() == null) {
            presenter.getUsersList(this);
            subscribe();

        } else {
            listUsersAdapter = new ListUsersAdapter(model.getUsers().getValue(), getContext());
        }
        listUsersRecyclerView = (RecyclerView) view.findViewById(R.id.list_item_container);
        defineRecyclerView(listUsersRecyclerView,listUsersAdapter);
        return view;
    }

    @Override
    public void usersReady() {
        listUsersAdapter = new ListUsersAdapter(model.getUsers().getValue(), getContext());
        listUsersRecyclerView.setAdapter(listUsersAdapter);
        listUsersAdapter.notifyDataSetChanged();
    }

    @Override
    public void render(UsersModel usersModel) {
        if (usersModel.getUsers() != null) {
            model.setUsers(usersModel.getUsers());
            usersReady();
        } else switch (usersModel.getError()) {
            case ERROR_CONNECTION:
                usersErrorConnectivity();
                break;
            case ERROR_RESPONSE:
                usersErrorHttp();
                break;
        }
    }

    @Override
    public void usersErrorHttp() {
        snackBarMessage(R.string.http_error_message);
    }

    @Override
    public void usersErrorConnectivity() {
        snackBarMessage(R.string.communication_error_message);
    }

    @Override
    public void snackBarMessage(int stringResource) {
        ((HomeScreenActivity) getActivity()).simpleSnackBarMessage(getString(stringResource));
    }

    private void subscribe() {
        final Observer<List<ModelUserEntity>> usersObserver = new Observer<List<ModelUserEntity>>() {
            @Override
            public void onChanged(@Nullable List<ModelUserEntity> userEntityList) {
                listUsersAdapter = new ListUsersAdapter(userEntityList, getContext());
                defineRecyclerView(listUsersRecyclerView,listUsersAdapter);
                listUsersAdapter.notifyDataSetChanged();
            }
        };
        model.getUsers().observe(this, usersObserver);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.registerBus();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.unregisterBus();
    }

    private void defineRecyclerView(RecyclerView listUsersRecyclerView, RecyclerView.Adapter adapter){
        listUsersRecyclerView.setHasFixedSize(true);
        listUsersRecyclerView.setLayoutManager(linearLayoutManager);
        listUsersRecyclerView.addItemDecoration(itemDecoration);
        listUsersRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
