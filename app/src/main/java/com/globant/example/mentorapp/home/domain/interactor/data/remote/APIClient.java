package com.globant.example.mentorapp.home.domain.interactor.data.remote;

import android.support.annotation.NonNull;

import com.globant.example.mentorapp.home.domain.interactor.data.util.ApiUtils;
import com.globant.example.mentorapp.home.domain.model.EventApiResponseEntity;
import com.globant.example.mentorapp.home.domain.model.UserEntity;
import com.squareup.otto.Bus;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Network client to use retrofit to download from endpoint.
 * Created by oscar.amezquita on 12/06/2017.
 */

public class APIClient {

    private Retrofit client;
    private Bus bus;

    @Inject
    public APIClient(Retrofit client, Bus bus) {
        this.client = client;
        this.bus = bus;
    }

    /**
     * Obtain a list of Users from service, Response is send using otto using Asyncronous form.
     */
    public void getUsersListFromAsyncService() {
        final EventApiResponseEntity.Builder builderEntity = new EventApiResponseEntity.Builder();
        client.create(APIService.class)
                .getUsers(ApiUtils.PROFILE_NAME_GITHUB, ApiUtils.PROFILE_REPOSITORY)
                .enqueue(new Callback<List<UserEntity>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<UserEntity>> call, @NonNull Response<List<UserEntity>> response) {

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
