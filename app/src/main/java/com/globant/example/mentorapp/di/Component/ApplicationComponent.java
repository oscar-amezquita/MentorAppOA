package com.globant.example.mentorapp.di.Component;

import javax.inject.Singleton;

import com.globant.example.mentorapp.di.Module.ApplicationModule;
import com.globant.example.mentorapp.home.presentation.view.activity.HomeScreenActivity;
import com.globant.example.mentorapp.home.presentation.view.fragment.ListUsersViewFragment;

import dagger.Component;

/**
 * Component class to control dependency injection.
 * Created by oscar.amezquita on 8/06/2017.
 */

@Singleton
@Component(modules = {ApplicationModule.class })
public interface ApplicationComponent {
    void inject(HomeScreenActivity activity);

    void inject(ListUsersViewFragment fragment);

}
