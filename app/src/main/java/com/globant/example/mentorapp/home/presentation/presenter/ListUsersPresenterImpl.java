package com.globant.example.mentorapp.home.presentation.presenter;

import com.globant.example.mentorapp.home.domain.interactor.FetchUserListInteractor;
import com.globant.example.mentorapp.home.domain.model.EventApiResponseEntity;
import com.globant.example.mentorapp.home.domain.model.UserEntity;
import com.globant.example.mentorapp.home.presentation.model.ListUsersViewModel;
import com.globant.example.mentorapp.home.presentation.model.ModelUserEntity;
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

public class ListUsersPresenterImpl extends BasePresenter implements ListUsersPresenter {

    private FetchUserListInteractor interactor;

    @Inject
    public ListUsersPresenterImpl(FetchUserListInteractor interactor, Bus bus) {
        this.interactor = interactor;
        this.bus = bus;
    }

    @Override
    public void getUsersList() {
        if (isViewAttached()) {
            baseView.render(new ListUsersViewModel(null, null, true));
            interactor.execute();
        }
    }

    @Subscribe
    public void controlResponse(EventApiResponseEntity<List<UserEntity>> result) {
        if (isViewAttached()) {
            baseView.render(new ListUsersViewModel(null, null, false));
            switch (result.getResponseCode()) {
            case EventApiResponseEntity.HTTP_OK:
                baseView.render(new ListUsersViewModel(translateModel(result.getList()), null, null));
                break;
            case EventApiResponseEntity.CONNECTION_ERROR:
                baseView.render(
                        new ListUsersViewModel(null, ListUsersViewModel.errorResponse.ERROR_CONNECTION, null));
                break;
            default:
                baseView.render(
                        new ListUsersViewModel(null, ListUsersViewModel.errorResponse.ERROR_RESPONSE, null));
                break;
            }
        }
    }

    /**
     * Translate Domain UserEntity list to Presentation ModelUserEntity list
     *
     * @param users List of {@link UserEntity} domain users
     * @return List of {@link ModelUserEntity} presentation users
     */
    private List<ModelUserEntity> translateModel(List<UserEntity> users) {
        List<ModelUserEntity> result = new ArrayList<>();
        for (UserEntity userEntity : users) {
            result.add(new ModelUserEntity(userEntity.getLogin(), userEntity.getAvatarUrl(), userEntity.getUrl()));
        }
        return result;
    }

}
