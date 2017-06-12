package com.globant.example.mentorapp.presentation.di.Component;

import com.globant.example.mentorapp.presentation.di.Module.UserModule;
import com.globant.example.mentorapp.presentation.view.fragment.ListUsersFragment;

import dagger.Component;

/**
 * Created by oscar.amezquita on 8/06/2017.
 */


@Component(modules = UserModule.class)
public interface UserComponent {
    void inject(ListUsersFragment fragment);
}
