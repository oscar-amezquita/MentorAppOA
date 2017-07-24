package com.globant.example.mentorapp.subscriberDetails.presentation.view.fragment;


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
import android.widget.ImageView;
import android.widget.TextView;

import com.globant.example.mentorapp.MentorApplication;
import com.globant.example.mentorapp.R;
import com.globant.example.mentorapp.data.livedata.SharedViewModel;
import com.globant.example.mentorapp.mvp.base.BaseActivity;
import com.globant.example.mentorapp.mvp.base.BaseView;
import com.globant.example.mentorapp.subscriberDetails.presentation.model.RepositoryModel;
import com.globant.example.mentorapp.subscriberDetails.presentation.model.UserDetailsModel;
import com.globant.example.mentorapp.subscriberDetails.presentation.model.UserDetailsViewModel;
import com.globant.example.mentorapp.subscriberDetails.presentation.presenter.UserDetailsPresenter;
import com.globant.example.mentorapp.subscriberDetails.presentation.view.adapter.UserRepositoriesAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

/**
 * A placeholder fragment some details of the selected user.
 */
public class UserDetailsFragment extends LifecycleFragment implements BaseView<UserDetailsViewModel> {

    @Inject
    public UserDetailsPresenter presenter;
    private SharedViewModel model;
    private BaseActivity parent;
    private UserRepositoriesAdapter listRepoAdapter;
    private RecyclerView listReposRecyclerView;
    private UserDetailsModel userDetails;
    private TextView userName;
    private TextView userLocation;
    private TextView company;
    private TextView followers;
    private TextView following;
    private TextView totalRepositories;
    private ImageView userImageView;

    public static UserDetailsFragment getInstance() {
        return new UserDetailsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MentorApplication) getActivity().getApplicationContext()).getApplicationComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_details, container, false);
        model = ViewModelProviders.of(this).get(SharedViewModel.class);
        parent = (BaseActivity) getActivity();

        listReposRecyclerView = (RecyclerView) view.findViewById(R.id.list_repo_container);
        userName = (TextView) view.findViewById(R.id.txtUserName);
        userLocation = (TextView) view.findViewById(R.id.txtLocation);
        company = (TextView) view.findViewById(R.id.txtCompany);
        followers = (TextView) view.findViewById(R.id.txtFollowers);
        following = (TextView) view.findViewById(R.id.txtFollowing);
        totalRepositories = (TextView) view.findViewById(R.id.txtRepositories);
        userImageView = (ImageView) view.findViewById(R.id.imgDetailUser);

        if (model.getUserDetails().getValue() != null && model.getUserRepos().getValue() != null) {
            listRepoAdapter = new UserRepositoriesAdapter(model.getUserRepos().getValue());
            totalRepositories.setText(
                    String.format(getActivity().getString(R.string.tv_detail_repositories), model.getUserRepos().getValue().size()));
            userDetails = model.getUserDetails().getValue();
            usersRepositoryListReady(listReposRecyclerView, listRepoAdapter);
            parent.hideProgress();
        }
        if (userDetails != null) {
            setUserInformation(userDetails);
        }
        if (listRepoAdapter != null) {
            usersRepositoryListReady(listReposRecyclerView, listRepoAdapter);
        }

        listReposRecyclerView.setAdapter(listRepoAdapter);
        return view;
    }

    @Override
    public void render(UserDetailsViewModel detailsViewModel) {
        if (detailsViewModel.getUserDetails() != null) {
            model.setUsersDetails(detailsViewModel.getUserDetails());
            setUserInformation(detailsViewModel.getUserDetails());
        }
        if (detailsViewModel.getUserRepos() != null) {
            model.setUserRepos(detailsViewModel.getUserRepos());
            totalRepositories.setText(
                    String.format(getActivity().getString(R.string.tv_detail_repositories), detailsViewModel.getUserRepos().size()));
            usersRepositoryListReady(listReposRecyclerView, listRepoAdapter);
        }
        if (detailsViewModel.getError() != null) {
            switch (detailsViewModel.getError()) {
                case ERROR_CONNECTION:
                    usersErrorConnectivity();
                    break;
                case ERROR_RESPONSE:
                    usersErrorHttp();
                    break;
            }
        }
        if (detailsViewModel.getProgress()) {
            parent.showProgress();
        } else {
            parent.hideProgress();
        }
    }

    private void usersErrorHttp() {
        parent.simpleSnackBarMessage(getString(R.string.http_error_message));
    }

    private void usersErrorConnectivity() {
        parent.simpleSnackBarMessage(getString(R.string.communication_error_message));
    }

    private void subscribeRepoList() {
        final Observer<List<RepositoryModel>> usersObserver = new Observer<List<RepositoryModel>>() {
            @Override
            public void onChanged(@Nullable List<RepositoryModel> repositoryViewModelList) {
                listRepoAdapter = new UserRepositoriesAdapter(repositoryViewModelList);
                usersRepositoryListReady(listReposRecyclerView, listRepoAdapter);
            }
        };
        model.getUserRepos().observe(this, usersObserver);
    }

    private void subscribeUserDetails() {
        final Observer<UserDetailsModel> usersObserver = new Observer<UserDetailsModel>() {
            @Override
            public void onChanged(@Nullable UserDetailsModel detailsModel) {
                setUserInformation(detailsModel);
            }
        };
        model.getUserDetails().observe(this, usersObserver);
    }

    private void setUserInformation(UserDetailsModel userDetails) {
        userName.setText(
                String.format(getActivity().getString(R.string.tv_detail_user_name), userDetails.getUserName()));
        userLocation.setText(
                String.format(getActivity().getString(R.string.tv_detail_location), userDetails.getLocation()));
        company.setText(
                String.format(getActivity().getString(R.string.tv_detail_company), userDetails.getCompany()));
        followers.setText(
                String.format(getActivity().getString(R.string.tv_detail_followers), userDetails.getFollowers()));
        following.setText(
                String.format(getActivity().getString(R.string.tv_detail_following), userDetails.getFollowing()));

        Picasso.with(getActivity()).load(userDetails.getImageUrl())
                .error(R.drawable.ic_not_found)
                .placeholder(R.drawable.ic_placeholder)
                .into(userImageView);
    }

    private void usersRepositoryListReady(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.addItemDecoration(
                new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.registerBus();
        presenter.attachView(this);
        if (model.getUserDetails().getValue() == null || model.getUserRepos() == null) {
            presenter.getUserDetails(getArguments().getString(getString(R.string.bundle_selected_user_id)));
            subscribeRepoList();
            subscribeUserDetails();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.unregisterBus();
        presenter.detachView();
    }
}
