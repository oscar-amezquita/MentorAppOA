package com.globant.example.mentorapp.home.presentation.view.fragment;

import com.globant.example.mentorapp.home.presentation.model.ListUsersViewModel;

/**
 * Interface to define List of users  basic behaviors
 * Created by oscar.amezquita on 9/06/2017.
 */

public interface ListUsersViewInterface {


    /**
     * Provides SnackBar feature to deploy informative messages
     */
    void snackBarMessage(int stringResource);

    /**
     * Method to organize UI actions in view layer
     */
    void render(ListUsersViewModel model);

}
