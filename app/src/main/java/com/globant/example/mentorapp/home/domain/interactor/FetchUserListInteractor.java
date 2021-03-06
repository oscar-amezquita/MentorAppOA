package com.globant.example.mentorapp.home.domain.interactor;

import android.support.annotation.NonNull;

import com.globant.example.mentorapp.data.remote.APIService;
import com.globant.example.mentorapp.data.util.ApiUtils;
import com.globant.example.mentorapp.home.domain.model.UserEntity;
import com.globant.example.mentorapp.home.domain.response.UserListResponseEntity;
import com.globant.example.mentorapp.mvp.base.BaseInteractor;
import com.squareup.otto.Bus;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This class runs all web service users queries.
 * Created by oscar.amezquita on 9/06/2017.
 */

public class FetchUserListInteractor extends BaseInteractor {

    @Inject
    public FetchUserListInteractor(APIService apiService, Bus bus) {
        super(apiService, bus);
    }

    /**
     * Obtain a list of Users from service, Response is send using otto using Asyncronous form.
     */
    public void execute() {
        final UserListResponseEntity.UserListResponseEntityBuilder builderEntity = new UserListResponseEntity.UserListResponseEntityBuilder();
        apiService.getUsers(ApiUtils.PROFILE_NAME_GITHUB, ApiUtils.PROFILE_REPOSITORY)
                .enqueue(new Callback<List<UserEntity>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<UserEntity>> call,
                                           @NonNull Response<List<UserEntity>> response) {
                        builderEntity.withResponseText(response.message());
                        builderEntity.withResponseCode(response.code());
                        if (response.isSuccessful()) {
                            builderEntity.withResponseCode(UserListResponseEntity.HTTP_OK);
                            builderEntity.withEntityList(response.body());
                        }
                        bus.post(builderEntity.build());
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<UserEntity>> call, @NonNull Throwable t) {
                        builderEntity.withResponseText(ApiUtils.SERVICE_RESPONSE_COMM_ERROR);
                        builderEntity.withResponseCode(UserListResponseEntity.CONNECTION_ERROR);
                        bus.post(builderEntity.build());
                    }
                });
    }

}
