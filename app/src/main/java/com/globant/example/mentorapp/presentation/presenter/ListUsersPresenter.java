package com.globant.example.mentorapp.presentation.presenter;

import android.support.annotation.NonNull;

import com.globant.example.mentorapp.domain.interactor.ListUsersInteractorImpl;
import com.globant.example.mentorapp.presentation.model.SharedUserViewModel;
import com.globant.example.mentorapp.presentation.view.fragment.ListUsersFragment;

import retrofit2.Retrofit;

/**
 * Interface to define the basic functions to return information to the list of users.
 * Created by oscar.amezquita on 7/06/2017.
 */

public interface ListUsersPresenter {

    void getUsersFromService(@NonNull ListUsersFragment listUsersFragment, SharedUserViewModel model);

}
