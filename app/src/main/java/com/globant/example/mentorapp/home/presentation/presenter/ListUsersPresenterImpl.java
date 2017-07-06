package com.globant.example.mentorapp.home.presentation.presenter;

import com.globant.example.mentorapp.home.domain.interactor.FetchUserListInteractor;
import com.globant.example.mentorapp.home.domain.model.EventApiResponseEntity;
import com.globant.example.mentorapp.home.domain.model.UserEntity;
import com.globant.example.mentorapp.home.presentation.model.ListUsersViewModel;
import com.globant.example.mentorapp.home.presentation.model.ModelUserEntity;
import com.globant.example.mentorapp.mvp.base.BaseModel;
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
        super(bus);
        this.interactor = interactor;
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
            view.render(new ListUsersViewModel(null, null, false));
            switch (result.getResponseCode()) {
                case EventApiResponseEntity.HTTP_OK:
                    view.render(new ListUsersViewModel(translateModel(result.getData()), null, null));
                    break;
                case EventApiResponseEntity.CONNECTION_ERROR:
                    view.render(
                            new ListUsersViewModel(null, BaseModel.ErrorResponse.ERROR_CONNECTION, null));
                    break;
                default:
                    view.render(
                            new ListUsersViewModel(null, BaseModel.ErrorResponse.ERROR_RESPONSE, null));
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
    protected List<ModelUserEntity> translateModel(List<UserEntity> users) {
        List<ModelUserEntity> result = new ArrayList<>();
        for (UserEntity userEntity : users) {
            result.add(new ModelUserEntity(userEntity.getLogin(), userEntity.getAvatarUrl(), userEntity.getUrl()));
        }
        return result;
    }

}
