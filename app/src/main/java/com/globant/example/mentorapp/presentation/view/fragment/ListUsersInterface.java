package com.globant.example.mentorapp.presentation.view.fragment;

import com.globant.example.mentorapp.data.entity.UserEntity;

import java.util.List;

import javax.inject.Inject;

/**
 * Interface to define List of users  basic behaviors
 * Created by oscar.amezquita on 9/06/2017.
 */

public interface ListUsersInterface {

    void UsersReady();

    void UsersError();
}
