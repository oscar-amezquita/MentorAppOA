package com.globant.example.mentorapp.home.presentation.presenter;

import android.support.annotation.NonNull;

import com.globant.example.mentorapp.home.domain.model.UserEntity;
import com.globant.example.mentorapp.home.presentation.model.ModelUserEntity;
import com.globant.example.mentorapp.home.presentation.view.fragment.ListUsersFragment;
import com.globant.example.mentorapp.home.presentation.view.fragment.ListUsersInterface;

import java.util.List;

/**
 * Interface to define the basic functions to return information to the list of users.
 * Created by oscar.amezquita on 7/06/2017.
 */

public interface ListUsersPresenter {
    /**
     * Provides a list from Users with a provider
     * @param listUsersInterface View instance to pass events and interactions.
     */
    void getUsersList(@NonNull ListUsersInterface listUsersInterface);

    /**
     * Translate Domain UserEntity list to Presentation ModelUserEntity list
     * @param users List of {@link UserEntity} domain users
     * @return List of {@link ModelUserEntity} presentation users
     */
    List<ModelUserEntity> translateModel(List<UserEntity> users);

    void registerBus();

    void unregisterBus();
}
