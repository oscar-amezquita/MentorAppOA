package com.globant.example.mentorapp.presentation.view.fragment;

/**
 * Interface to define List of users  basic behaviors
 * Created by oscar.amezquita on 9/06/2017.
 */

public interface ListUsersInterface {

    void usersReady();

    void usersErrorHttp();

    void usersErrorConnectivity();

    void snackBarMessage(int stringResource);

}
