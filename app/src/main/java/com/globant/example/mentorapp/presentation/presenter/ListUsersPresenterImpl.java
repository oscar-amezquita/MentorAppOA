package com.globant.example.mentorapp.presentation.presenter;

import android.support.annotation.NonNull;

import com.globant.example.mentorapp.data.entity.UserEntity;
import com.globant.example.mentorapp.data.util.ApiUtils;
import com.globant.example.mentorapp.domain.interactor.ListUsersInteractorImpl;
import com.globant.example.mentorapp.presentation.di.Component.UserComponent;
import com.globant.example.mentorapp.presentation.model.SharedUserViewModel;
import com.globant.example.mentorapp.presentation.view.fragment.ListUsersFragment;
import com.globant.example.mentorapp.presentation.view.fragment.ListUsersInterface;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.List;

import javax.inject.Inject;

/**
 * This class controls the exchange of information between the view and the data sources
 * Created by oscar.amezquita on 7/06/2017.
 */

public class ListUsersPresenterImpl implements ListUsersPresenter {

    @Inject
    ListUsersInteractorImpl interactor;

    private ListUsersInterface listener;

    private ListUsersFragment listUsersFragment;

    @Inject
    Bus bus;

    public ListUsersPresenterImpl(UserComponent component) {
        component.inject(this);
    }

    @Override
    public void getUsersFromService(@NonNull ListUsersFragment listUsersFragment, SharedUserViewModel model) {
        this.listUsersFragment = listUsersFragment;
        interactor.getUsersListFromService(model);

    }

    @Subscribe
    public void controlResponse(String result) {
        switch (result) {
            case ApiUtils.SERVICE_RESPONSE_OK:
                listUsersFragment.UsersReady();
                break;
            default:
                listUsersFragment.UsersError();
                break;
        }
    }

    public void onResume() {
        bus.register(this);
    }

    public void onPause() {
        bus.unregister(this);
    }

}
