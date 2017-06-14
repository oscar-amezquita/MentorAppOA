package com.globant.example.mentorapp.home.presentation.view.fragment;

import com.globant.example.mentorapp.home.presentation.model.UsersModel;

/**
 * Interface to define List of users  basic behaviors
 * Created by oscar.amezquita on 9/06/2017.
 */

public interface ListUsersInterface {

    /**
     * Method to recieve success response from presenter
     */
    void usersReady();

    /**
     * Method to provide http error messages
     */
    void usersErrorHttp();

    /**
     * Method to provide connectivity messages
     */
    void usersErrorConnectivity();

    /**
     * Provides SnackBar feature to deploy informative messages
     */
    void snackBarMessage(int stringResource);

    /**
     * Method to organize UI actions in view layer
     */
    void render(UsersModel model);

}
