package com.globant.example.mentorapp.home.presentation.presenter;

import com.globant.example.mentorapp.home.domain.interactor.ListUsersInteractorImpl;
import com.globant.example.mentorapp.home.domain.model.EventApiResponseEntity;
import com.globant.example.mentorapp.home.domain.model.UserEntity;
import com.globant.example.mentorapp.home.presentation.model.ListUsersViewModel;
import com.globant.example.mentorapp.home.presentation.model.ModelUserEntity;
import com.globant.example.mentorapp.home.presentation.view.fragment.ListUsersViewInterface;
import com.globant.example.mentorapp.mvp.base.BasePresenter;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * This class controls the exchange of information between the view and the data sources
 * Created by oscar.amezquita on 7/06/2017.
 */

public class ListUsersPresenterImpl extends BasePresenter implements ListUsersPresenter<ListUsersViewInterface> {

    ListUsersInteractorImpl interactor;
    ListUsersViewInterface listUsersViewInterface;

    @Inject
    public ListUsersPresenterImpl(ListUsersInteractorImpl interactor, Bus bus) {
        this.interactor = interactor;
        this.bus = bus;
    }

    @Override
    public void getUsersList() {
        interactor.getUsersList();
    }

    @Subscribe
    public void controlResponse(EventApiResponseEntity result) {
        if (isViewAttached()) {
            switch (result.getResponseCode()) {
                case EventApiResponseEntity.HTTP_OK:
                    listUsersViewInterface.render(new ListUsersViewModel(
                            translateModel(result.getList()), null));
                    break;
                case EventApiResponseEntity.CONNECTION_ERROR:
                    listUsersViewInterface.render(new ListUsersViewModel(
                            null, ListUsersViewModel.errorResponse.ERROR_CONNECTION));
                    break;
                default:
                    listUsersViewInterface.render(new ListUsersViewModel(
                            null, ListUsersViewModel.errorResponse.ERROR_RESPONSE));
                    break;
            }
        }
    }

    @Override
    public List<ModelUserEntity> translateModel(List<UserEntity> users) {
        List<ModelUserEntity> result = new ArrayList<>();
        for (UserEntity userEntity : users) {
            result.add(new ModelUserEntity(userEntity.getLogin(), userEntity.getAvatarUrl(), userEntity.getUrl()));
        }
        return result;
    }

    @Override
    public void attachView(ListUsersViewInterface view) {
        listUsersViewInterface = view;
    }

    @Override
    public void detachView() {
        listUsersViewInterface = null;
    }

    @Override
    public boolean isViewAttached() {
        return listUsersViewInterface != null;
    }


}
