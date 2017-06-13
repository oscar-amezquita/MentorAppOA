package com.globant.example.mentorapp.data.remote;

import android.support.annotation.NonNull;

import com.globant.example.mentorapp.data.entity.UserEntity;
import com.globant.example.mentorapp.data.util.ApiUtils;
import com.globant.example.mentorapp.presentation.di.Component.UserComponent;
import com.globant.example.mentorapp.presentation.model.SharedUserViewModel;
import com.squareup.otto.Bus;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by oscar.amezquita on 12/06/2017.
 */

public class APIClient {

    @Inject
    Retrofit client;
    @Inject
    Bus bus;


    public APIClient(UserComponent component) {
        component.inject(this);
    }

    /**
     * Obtain a list of Users from service
     *
     * @return
     */
    public void getUsersListFromService(final SharedUserViewModel model) {

        client.create(APIService.class).getUsers(ApiUtils.PROFILE_NAME_GITHUB, ApiUtils.PROFILE_REPOSITORY).enqueue(new Callback<List<UserEntity>>() {
            @Override
            public void onResponse(@NonNull Call<List<UserEntity>> call, @NonNull Response<List<UserEntity>> response) {
                if (response.isSuccessful()) {
                    model.setUsers(response.body());
                    bus.post(ApiUtils.SERVICE_RESPONSE_OK);
                } else {
                    bus.post(ApiUtils.SERVICE_RESPONSE_HTTP_ERROR);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<UserEntity>> call, @NonNull Throwable t) {
                bus.post(ApiUtils.SERVICE_RESPONSE_COMM_ERROR);
            }
        });
    }
}
