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
import com.globant.example.mentorapp.home.presentation.model.ListUsersViewModel;
import com.globant.example.mentorapp.home.presentation.model.ModelUserEntity;
import com.globant.example.mentorapp.home.presentation.model.SharedUserViewModel;
import com.globant.example.mentorapp.home.presentation.presenter.ListUsersPresenterImpl;
import com.globant.example.mentorapp.home.presentation.view.adapter.ListUsersAdapter;
import com.globant.example.mentorapp.mvp.base.BaseActivity;
import com.globant.example.mentorapp.mvp.base.BaseModel;
import com.globant.example.mentorapp.mvp.base.BaseView;

import java.util.List;

import javax.inject.Inject;

/**
 * A placeholder fragment containing a list of Users with his name.
 */
public class ListUsersViewFragment extends LifecycleFragment implements BaseView {

    public static final String LIST_TAG = "listUserFragment";
    @Inject
    public ListUsersPresenterImpl presenter;
    protected StaggeredGridLayoutManager staggeredGridLayoutManager;
    private SharedUserViewModel model;
    private ListUsersAdapter listUsersAdapter;
    private RecyclerView listUsersRecyclerView;
    private BaseActivity parent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MentorApplication) getActivity().getApplicationContext()).getApplicationComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_screen, container, false);
        model = ViewModelProviders.of(this).get(SharedUserViewModel.class);
        parent = (BaseActivity) getActivity();
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(
                getResources().getInteger(R.integer.number_of_users_columns), LinearLayoutManager.VERTICAL);
        if (model.getUsers() != null || model.getUsers().getValue() != null) {
            listUsersAdapter = new ListUsersAdapter(model.getUsers().getValue(), getContext());
            parent.hideProgress();
        }
        listUsersRecyclerView = (RecyclerView) view.findViewById(R.id.list_item_container);
        defineRecyclerView(listUsersRecyclerView, listUsersAdapter);
        return view;
    }

    @Override
    public void render(BaseModel baseModel) {
        ListUsersViewModel usersModel = (ListUsersViewModel) baseModel;
        if (usersModel.getUsers() != null) {
            model.setUsers(usersModel.getUsers());
            usersReady();
        } else if (usersModel.getError() != null)
            switch (usersModel.getError()) {
                case ERROR_CONNECTION:
                    usersErrorConnectivity();
                    break;
                case ERROR_RESPONSE:
                    usersErrorHttp();
                    break;
            }
        if (usersModel.getProgress() != null) {
            if (usersModel.getProgress()) {
                parent.showProgress();
            } else {
                parent.hideProgress();
            }
        }
    }

    private void usersReady() {
        listUsersAdapter = new ListUsersAdapter(model.getUsers().getValue(), getContext());
        listUsersRecyclerView.setAdapter(listUsersAdapter);
        listUsersAdapter.notifyDataSetChanged();
    }

    private void usersErrorHttp() {
        snackBarMessage(R.string.http_error_message);
    }

    private void usersErrorConnectivity() {
        snackBarMessage(R.string.communication_error_message);
    }

    private void snackBarMessage(int stringResource) {
        parent.simpleSnackBarMessage(getString(stringResource));
    }

    private void subscribe() {
        final Observer<List<ModelUserEntity>> usersObserver = new Observer<List<ModelUserEntity>>() {
            @Override
            public void onChanged(@Nullable List<ModelUserEntity> userEntityList) {
                listUsersAdapter = new ListUsersAdapter(userEntityList, getContext());
                defineRecyclerView(listUsersRecyclerView, listUsersAdapter);
                listUsersAdapter.notifyDataSetChanged();
            }
        };
        model.getUsers().observe(this, usersObserver);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onRegisterBus();
        presenter.attachView(this);
        if (model.getUsers().getValue() == null) {
            presenter.getUsersList();
            subscribe();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onUnregisterBus();
        presenter.detachView();
    }

    private void defineRecyclerView(RecyclerView listUsersRecyclerView, RecyclerView.Adapter adapter) {
        listUsersRecyclerView.setHasFixedSize(true);
        listUsersRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        listUsersRecyclerView.setAdapter(adapter);
    }

}
