package com.globant.example.mentorapp.home.presentation.presenter;

import android.support.annotation.NonNull;

import com.globant.example.mentorapp.di.Component.ApplicationComponent;
import com.globant.example.mentorapp.home.domain.interactor.ListUsersInteractor;
import com.globant.example.mentorapp.home.domain.model.EventApiResponseEntity;
import com.globant.example.mentorapp.home.domain.model.UserEntity;
import com.globant.example.mentorapp.home.presentation.model.ModelUserEntity;
import com.globant.example.mentorapp.home.presentation.model.UsersModel;
import com.globant.example.mentorapp.home.presentation.view.fragment.ListUsersInterface;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * This class controls the exchange of information between the view and the data sources
 * Created by oscar.amezquita on 7/06/2017.
 */

public class ListUsersPresenterImpl implements ListUsersPresenter {

    @Inject
    ListUsersInteractor interactor;
    @Inject
    Bus bus;

    private ListUsersInterface listUsersInterface;

    public ListUsersPresenterImpl(ApplicationComponent component) {
        component.inject(this);
    }

    @Override
    public void getUsersList(@NonNull ListUsersInterface listUsersInterface) {
        this.listUsersInterface = listUsersInterface;
        interactor.getUsersList();

    }

    @Subscribe
    public void controlResponse(EventApiResponseEntity result) {
        switch (result.getResponseCode()) {
            case EventApiResponseEntity.HTTP_OK:
                listUsersInterface.render(new UsersModel(translateModel(result.getUserEntityList()), null));
                break;
            case EventApiResponseEntity.CONNECTION_ERROR:
                listUsersInterface.render(new UsersModel(null, UsersModel.errorResponse.ERROR_CONNECTION));
                break;
            default:
                listUsersInterface.render(new UsersModel(null, UsersModel.errorResponse.ERROR_RESPONSE));
                break;
        }
    }

    @Override
    public List<ModelUserEntity> translateModel(List<UserEntity> users) {
        List<ModelUserEntity> result = new ArrayList<>();
        for (UserEntity usr : users) {
            result.add(new ModelUserEntity(usr.getLogin(), usr.getAvatarUrl(), usr.getUrl()));
        }
        return result;
    }

    public void registerBus() {
        bus.register(this);
    }

    public void unregisterBus() {
        bus.unregister(this);
    }


}
