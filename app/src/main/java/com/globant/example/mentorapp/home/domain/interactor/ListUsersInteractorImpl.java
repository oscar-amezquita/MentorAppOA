package com.globant.example.mentorapp.home.domain.interactor;

import com.globant.example.mentorapp.home.domain.interactor.data.remote.APIClient;

import javax.inject.Inject;

/**
 * This class runs all web service users queries.
 * Created by oscar.amezquita on 9/06/2017.
 */

public class ListUsersInteractorImpl implements ListUsersInteractor {


    private APIClient apiClient;

    @Inject
    public ListUsersInteractorImpl(APIClient apiClient) {
        this.apiClient = apiClient;
    }

    @Override
    public void getUsersList() {
        apiClient.getUsersListFromAsyncService();
    }

}
