package com.globant.example.mentorapp.mvp.base;

import com.globant.example.mentorapp.home.presentation.model.ListUsersViewModel;

/**
 * Interface to define List of users  basic behaviors
 * Created by oscar.amezquita on 9/06/2017.
 */

public interface BaseView {

    /**
     * Method to organize UI actions in view layer
     */
    void render(ListUsersViewModel model);

}
