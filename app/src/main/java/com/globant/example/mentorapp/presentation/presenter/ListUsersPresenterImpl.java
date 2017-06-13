package com.globant.example.mentorapp.presentation.presenter;

import android.support.annotation.NonNull;

import com.globant.example.mentorapp.data.entity.EventApiResponseEntity;
import com.globant.example.mentorapp.domain.interactor.ListUsersInteractorImpl;
import com.globant.example.mentorapp.presentation.di.Component.ApplicationComponent;
import com.globant.example.mentorapp.presentation.model.SharedUserViewModel;
import com.globant.example.mentorapp.presentation.view.fragment.ListUsersFragment;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

/**
 * This class controls the exchange of information between the view and the data sources
 * Created by oscar.amezquita on 7/06/2017.
 */

public class ListUsersPresenterImpl implements ListUsersPresenter {

    @Inject
    ListUsersInteractorImpl interactor;
    @Inject
    Bus bus;
    private ListUsersFragment listUsersFragment;

    public ListUsersPresenterImpl(ApplicationComponent component) {
        component.inject(this);
    }

    @Override
    public void getUsersFromService(@NonNull ListUsersFragment listUsersFragment, SharedUserViewModel model) {
        this.listUsersFragment = listUsersFragment;
        interactor.getUsersListFromService(model);

    }

    @Subscribe
    public void controlResponse(EventApiResponseEntity result) {
        switch (result.getResponseCode()) {
            case EventApiResponseEntity.HTTP_OK:
                listUsersFragment.usersReady();
                break;
            case EventApiResponseEntity.CONNECTION_ERROR:
                listUsersFragment.usersErrorConnectivity();
                break;
            default:
                listUsersFragment.usersErrorHttp();
                break;
        }
    }

    public void registerBus() {
        bus.register(this);
    }

    public void unregisterBus() {
        bus.unregister(this);
    }

}
