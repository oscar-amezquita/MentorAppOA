package com.globant.example.mentorapp;

import android.app.Application;

import com.globant.example.mentorapp.presentation.di.Component.DaggerUserComponent;
import com.globant.example.mentorapp.presentation.di.Component.UserComponent;
import com.globant.example.mentorapp.presentation.di.Module.ListUsersModule;

/**
 * Created by oscar.amezquita on 8/06/2017.
 */

public class MentorApplication extends Application {

    protected UserComponent userComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        userComponent = DaggerUserComponent.builder().listUsersModule(new ListUsersModule(this)).build();
    }

    public UserComponent getUserComponent() {
        return userComponent;
    }
}
