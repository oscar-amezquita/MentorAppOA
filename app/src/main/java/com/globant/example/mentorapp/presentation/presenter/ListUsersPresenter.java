package com.globant.example.mentorapp.presentation.presenter;

import com.globant.example.mentorapp.domain.interactor.ListUsersInteractorImpl;

import retrofit2.Retrofit;

/**
 * Interface to define the basic functions to return information to the list of users.
 * Created by oscar.amezquita on 7/06/2017.
 */

public interface ListUsersPresenter {

    void getUsersFromService(Retrofit client, ListUsersInteractorImpl interactor);
}
