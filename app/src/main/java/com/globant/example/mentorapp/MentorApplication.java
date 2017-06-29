package com.globant.example.mentorapp;

import com.globant.example.mentorapp.di.Component.ApplicationComponent;
import com.globant.example.mentorapp.di.Component.DaggerApplicationComponent;
import com.globant.example.mentorapp.di.Module.ApplicationModule;

import android.app.Application;

/**
 * Custom Application class to manage app resources
 * Created by oscar.amezquita on 8/06/2017.
 */

public class MentorApplication extends Application {

    protected ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
