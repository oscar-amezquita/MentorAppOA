package com.globant.example.mentorapp.home.domain.interactor.data.remote;

import android.support.annotation.NonNull;

import com.globant.example.mentorapp.di.Component.ApplicationComponent;
import com.globant.example.mentorapp.home.domain.interactor.data.util.ApiUtils;
import com.globant.example.mentorapp.home.domain.model.EventApiResponseEntity;
import com.globant.example.mentorapp.home.domain.model.UserEntity;
import com.squareup.otto.Bus;

import java.io.IOException;
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

    @Inject
    Retrofit client;
    @Inject
    Bus bus;
    @Inject
    EventApiResponseEntity responseEntity;

    public APIClient(ApplicationComponent component) {
        component.inject(this);
    }

    /**
     * Obtain a list of Users from service, Response is send using otto using Asyncronous form.
     */
    public void getUsersListFromAsyncService() {

        client.create(APIService.class).getUsers(ApiUtils.PROFILE_NAME_GITHUB, ApiUtils.PROFILE_REPOSITORY).enqueue(new Callback<List<UserEntity>>() {
            @Override
            public void onResponse(@NonNull Call<List<UserEntity>> call, @NonNull Response<List<UserEntity>> response) {
                responseEntity.setResponseText(response.message());
                responseEntity.setResponseCode(response.code());
                if (response.isSuccessful()) {
                    responseEntity.setResponseCode(EventApiResponseEntity.HTTP_OK);
                    responseEntity.setUserEntityList(response.body());
                }
                bus.post(responseEntity);
            }

            @Override
            public void onFailure(@NonNull Call<List<UserEntity>> call, @NonNull Throwable t) {
                bus.post(new EventApiResponseEntity(EventApiResponseEntity.CONNECTION_ERROR, ApiUtils.SERVICE_RESPONSE_COMM_ERROR, null));
            }
        });
    }

    /**
     * Obtain a list of Users from service, Response is send using otto using Syncronous form.
     * @return {@code true}if response is success, and {@code false} when the call fails.
     */
    public boolean getUsersListFromSyncService() {
                try {
                    Response<List<UserEntity>> result = client.create(APIService.class).getUsers(ApiUtils.PROFILE_NAME_GITHUB, ApiUtils.PROFILE_REPOSITORY).execute();
                    responseEntity.setResponseText(result.message());
                    responseEntity.setResponseCode(result.code());
                    if (result.isSuccessful()) {
                        responseEntity.setResponseCode(EventApiResponseEntity.HTTP_OK);
                        responseEntity.setUserEntityList(result.body());
                    }
                     bus.post(responseEntity);
                    return true;

                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }

    }
}
