package com.globant.example.mentorapp.domain.interactor;

import com.globant.example.mentorapp.data.remote.APIClient;
import com.globant.example.mentorapp.presentation.di.Component.ApplicationComponent;
import com.globant.example.mentorapp.presentation.model.SharedUserViewModel;

import javax.inject.Inject;

/**
 * This class runs all web service users queries.
 * Created by oscar.amezquita on 9/06/2017.
 */

public class ListUsersInteractorImpl implements ListUsersInteractor {

    @Inject
    APIClient apiClient;

    public ListUsersInteractorImpl(ApplicationComponent component) {
        component.inject(this);
    }

    @Override
    public void getUsersListFromService(SharedUserViewModel model) {
        apiClient.getUsersListFromService(model);
    }
}
