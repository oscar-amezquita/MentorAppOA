package com.globant.example.mentorapp;

import android.app.Application;

import com.globant.example.mentorapp.presentation.di.Component.ApplicationComponent;
import com.globant.example.mentorapp.presentation.di.Component.DaggerApplicationComponent;
import com.globant.example.mentorapp.presentation.di.Module.ApplicationModule;

/**
 * Custom Application class to manage app resources
 * Created by oscar.amezquita on 8/06/2017.
 */

public class MentorApplication extends Application {

    protected ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
