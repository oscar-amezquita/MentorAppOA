package com.globant.example.mentorapp.home.presentation.presenter;

import com.globant.example.mentorapp.home.domain.interactor.FetchUserListInteractor;
import com.globant.example.mentorapp.home.domain.model.EventApiResponseEntity;
import com.globant.example.mentorapp.home.domain.model.UserEntity;
import com.globant.example.mentorapp.home.presentation.model.ListUsersViewModel;
import com.globant.example.mentorapp.home.presentation.model.ModelUserEntity;
import com.globant.example.mentorapp.mvp.base.BaseModel;
import com.globant.example.mentorapp.mvp.base.BasePresenter;
import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Inject;

/**
 * This class controls the exchange of information between the view and the data sources
 * Created by oscar.amezquita on 7/06/2017.
 */

public class ListUsersPresenterImpl extends BasePresenter implements ListUsersPresenter {

    private FetchUserListInteractor interactor;

    @Inject
    public ListUsersPresenterImpl(FetchUserListInteractor interactor, Bus bus) {
        super(bus);
        this.interactor = interactor;
    }

    /**
     * Translate Domain UserEntity list to Presentation ModelUserEntity list
     */
    private static Function<UserEntity, ModelUserEntity> transformToModelUser() {
        return new Function<UserEntity, ModelUserEntity>() {
            @Nullable
            @Override
            public ModelUserEntity apply(@Nullable UserEntity input) {
                return new ModelUserEntity(input.getLogin(), input.getAvatarUrl(), input.getUrl());
            }
        };
    }

    @Override
    public void getUsersList() {
        if (isViewAttached()) {
            view.render(new ListUsersViewModel(null, null, true));
        }
        interactor.execute();
    }

    @Subscribe
    public void controlResponse(EventApiResponseEntity<List<UserEntity>> result) {
        if (isViewAttached()) {
            switch (result.getResponseCode()) {
                case EventApiResponseEntity.HTTP_OK:
                    view.render(new ListUsersViewModel(FluentIterable.from(result.getData()).transform(transformToModelUser()).toList(), null, false));
                    break;
                case EventApiResponseEntity.CONNECTION_ERROR:
                    view.render(
                            new ListUsersViewModel(null, BaseModel.ErrorResponse.ERROR_CONNECTION, false));
                    break;
                default:
                    view.render(
                            new ListUsersViewModel(null, BaseModel.ErrorResponse.ERROR_RESPONSE, false));
                    break;
            }
        }
    }

}
