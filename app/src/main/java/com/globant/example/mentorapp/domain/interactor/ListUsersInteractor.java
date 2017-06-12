package com.globant.example.mentorapp.domain.interactor;

import com.globant.example.mentorapp.presentation.view.fragment.ListUsersInterface;

import retrofit2.Retrofit;

/**
 * Created by oscar.amezquita on 9/06/2017.
 */

public interface ListUsersInteractor {

    void getUsersListFromService(ListUsersInterface listener, Retrofit client);

}
