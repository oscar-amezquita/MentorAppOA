package com.globant.example.mentorapp.di.Component;

import com.globant.example.mentorapp.di.Module.ApplicationModule;
import com.globant.example.mentorapp.home.domain.interactor.ListUsersInteractorImpl;
import com.globant.example.mentorapp.home.domain.interactor.data.remote.APIClient;
import com.globant.example.mentorapp.home.presentation.model.SharedUserViewModel;
import com.globant.example.mentorapp.home.presentation.presenter.ListUsersPresenterImpl;
import com.globant.example.mentorapp.home.presentation.view.activity.HomeScreenActivity;
import com.globant.example.mentorapp.home.presentation.view.fragment.ListUsersFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Component class to control dependency injection.
 * Created by oscar.amezquita on 8/06/2017.
 */

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    void inject(HomeScreenActivity activity);

    void inject(ListUsersFragment fragment);

    void inject(ListUsersPresenterImpl listUsersPresenter);

    void inject(ListUsersInteractorImpl listUsersInteractor);

    void inject(APIClient client);

    void inject(SharedUserViewModel sharedUserViewModel);
}
