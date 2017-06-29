package com.globant.example.mentorapp.home.domain.interactor;

import java.util.List;

import javax.inject.Inject;

import com.globant.example.mentorapp.home.domain.interactor.data.remote.APIService;
import com.globant.example.mentorapp.home.domain.interactor.data.util.ApiUtils;
import com.globant.example.mentorapp.home.domain.model.EventApiResponseEntity;
import com.globant.example.mentorapp.home.domain.model.UserEntity;
import com.squareup.otto.Bus;

import android.support.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This class runs all web service users queries.
 * Created by oscar.amezquita on 9/06/2017.
 */

public class ListUsersInteractor {

    private APIService apiService;
    private Bus bus;

    @Inject
    public ListUsersInteractor(APIService apiService, Bus bus) {
        this.apiService = apiService;
        this.bus = bus;
    }

    public void getUsersList() {
        getUsersListFromAsyncService();
    }

    /**
     * Obtain a list of Users from service, Response is send using otto using Asyncronous form.
     */
    private void getUsersListFromAsyncService() {
        final EventApiResponseEntity.Builder<List<UserEntity>> builderEntity = new EventApiResponseEntity.Builder<>();
        apiService.getUsers(ApiUtils.PROFILE_NAME_GITHUB, ApiUtils.PROFILE_REPOSITORY)
                .enqueue(new Callback<List<UserEntity>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<UserEntity>> call,
                            @NonNull Response<List<UserEntity>> response) {
                        builderEntity.withResponseText(response.message());
                        builderEntity.withResponseCode(response.code());
                        if (response.isSuccessful()) {
                            builderEntity.withResponseCode(EventApiResponseEntity.HTTP_OK);
                            builderEntity.withUserEntityList(response.body());
                        }
                        bus.post(builderEntity.build());
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<UserEntity>> call, @NonNull Throwable t) {
                        builderEntity.withUserEntityList(null);
                        builderEntity.withResponseText(ApiUtils.SERVICE_RESPONSE_COMM_ERROR);
                        builderEntity.withResponseCode(EventApiResponseEntity.CONNECTION_ERROR);
                        bus.post(builderEntity.build());
                    }
                });
    }

}
