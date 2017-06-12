package com.globant.example.mentorapp.presentation.presenter;

import com.globant.example.mentorapp.domain.interactor.ListUsersInteractorImpl;
import com.globant.example.mentorapp.presentation.view.fragment.ListUsersInterface;

import retrofit2.Retrofit;

/**
 * This class controls the exchange of information between the view and the data sources
 * Created by oscar.amezquita on 7/06/2017.
 */

public class ListUsersPresenterImpl implements ListUsersPresenter {


    private ListUsersInterface listener;

    public ListUsersPresenterImpl(ListUsersInterface listener) {
        this.listener = listener;
    }

    @Override
    public void getUsersFromService(Retrofit client, ListUsersInteractorImpl interactor) {
        interactor.getUsersListFromService(listener, client);

    }
}
