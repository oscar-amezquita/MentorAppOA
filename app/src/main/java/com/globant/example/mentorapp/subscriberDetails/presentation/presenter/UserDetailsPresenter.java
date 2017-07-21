package com.globant.example.mentorapp.subscriberDetails.presentation.presenter;

import com.globant.example.mentorapp.data.remote.EventApiResponseEntity;
import com.globant.example.mentorapp.mvp.base.BaseModel;
import com.globant.example.mentorapp.mvp.base.BasePresenter;
import com.globant.example.mentorapp.subscriberDetails.domain.interactor.FetchReposListInteractor;
import com.globant.example.mentorapp.subscriberDetails.domain.interactor.FetchUserDetailsInteractor;
import com.globant.example.mentorapp.subscriberDetails.domain.model.ListReposEntity;
import com.globant.example.mentorapp.subscriberDetails.domain.model.UserDetailEntity;
import com.globant.example.mentorapp.subscriberDetails.domain.response.RepoListResponseEntity;
import com.globant.example.mentorapp.subscriberDetails.domain.response.UserDetailResponseEntity;
import com.globant.example.mentorapp.subscriberDetails.presentation.model.DetailsModel;
import com.globant.example.mentorapp.subscriberDetails.presentation.model.RepositoryModel;
import com.globant.example.mentorapp.subscriberDetails.presentation.model.UserDetailsViewModel;
import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import javax.annotation.Nullable;
import javax.inject.Inject;

/**
 * This class controls the exchange of user details between the view and the data sources
 * Created by oscar.amezquita on 14/07/2017.
 */

public class UserDetailsPresenter extends BasePresenter {

    private FetchUserDetailsInteractor fetchUserDetailsInteractor;
    private FetchReposListInteractor fetchReposListInteractor;
    private String userId;

    @Inject
    public UserDetailsPresenter(FetchUserDetailsInteractor fetchUserDetailsInteractor, FetchReposListInteractor fetchReposListInteractor, Bus bus) {
        super(bus);
        this.fetchUserDetailsInteractor = fetchUserDetailsInteractor;
        this.fetchReposListInteractor = fetchReposListInteractor;
    }

    /**
     * Translate Domain UserEntity list to Presentation ModelUserEntity list
     */
    private static Function<UserDetailEntity, DetailsModel> transformToModelUser() {
        return new Function<UserDetailEntity, DetailsModel>() {
            @Nullable
            @Override
            public DetailsModel apply(@Nullable UserDetailEntity input) {
                DetailsModel.Builder builder = new DetailsModel.Builder();
                builder.withUserName(input.getName())
                        .withCompany(input.getCompany())
                        .withLocation(input.getLocation())
                        .withFolllowers(input.getFollowers())
                        .withFollowing(input.getFollowing())
                        .withImageUrl(input.getAvatarUrl());
                return builder.build();
            }
        };
    }

    /**
     * Translate Domain ListReposEntity list to Presentation RepositoryModel list
     */
    private static Function<ListReposEntity, RepositoryModel> transformToRepoList() {
        return new Function<ListReposEntity, RepositoryModel>() {
            @Nullable
            @Override
            public RepositoryModel apply(@Nullable ListReposEntity input) {
                return new RepositoryModel(input.getName(), input.getHtmlUrl());
            }
        };
    }

    public void getUserDetails(String userId) {
        if (isViewAttached()) {
            view.render(new UserDetailsViewModel(null, null, null, true));
        }
        fetchUserDetailsInteractor.execute(userId);
    }

    @Subscribe
    public void controlResponseUserDetails(UserDetailResponseEntity result) {
        if (isViewAttached()) {
            switch (result.getResponseCode()) {
                case EventApiResponseEntity.HTTP_OK:
                    view.render(new UserDetailsViewModel(transformToModelUser().apply(result.getData()), null, null, false));
                    fetchReposListInteractor.execute(result.getData().getLogin());
                    break;
                case EventApiResponseEntity.CONNECTION_ERROR:
                    view.render(
                            new UserDetailsViewModel(null, null, BaseModel.ErrorResponse.ERROR_CONNECTION, false));
                    break;
                default:
                    view.render(
                            new UserDetailsViewModel(null, null, BaseModel.ErrorResponse.ERROR_RESPONSE, false));
                    break;
            }
        }
    }

    @Subscribe
    public void controlResponseRepositoryList(RepoListResponseEntity result) {
        if (isViewAttached()) {
            switch (result.getResponseCode()) {
                case EventApiResponseEntity.HTTP_OK:
                    view.render(new UserDetailsViewModel(
                            null, FluentIterable.from(result.getData()).transform(transformToRepoList()).toList(), null, false));
                    break;
                case EventApiResponseEntity.CONNECTION_ERROR:
                    view.render(
                            new UserDetailsViewModel(null, null, BaseModel.ErrorResponse.ERROR_CONNECTION, false));
                    break;
                default:
                    view.render(
                            new UserDetailsViewModel(null, null, BaseModel.ErrorResponse.ERROR_RESPONSE, false));
                    break;
            }
        }
    }

}
