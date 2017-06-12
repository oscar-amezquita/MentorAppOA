package com.globant.example.mentorapp;

import android.app.Application;

import com.globant.example.mentorapp.presentation.di.Component.DaggerUserComponent;
import com.globant.example.mentorapp.presentation.di.Component.UserComponent;
import com.globant.example.mentorapp.presentation.di.Module.UserModule;

/**
 * Created by oscar.amezquita on 8/06/2017.
 */

public class MentorApplication extends Application {

    private UserComponent userComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        userComponent = DaggerUserComponent.builder().userModule(new UserModule(this)).build();
    }

    public UserComponent getUserComponent() {
        return userComponent;
    }
}
