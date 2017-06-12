package com.globant.example.mentorapp.domain.interactor;

import android.content.Context;
import android.support.annotation.NonNull;

import com.globant.example.mentorapp.R;
import com.globant.example.mentorapp.data.entity.UserEntity;
import com.globant.example.mentorapp.data.remote.APIService;
import com.globant.example.mentorapp.data.remote.ApiUtils;
import com.globant.example.mentorapp.presentation.view.fragment.ListUsersInterface;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * This class runs all web service users queries.
 * Created by oscar.amezquita on 9/06/2017.
 */

public class ListUsersInteractorImpl implements ListUsersInteractor {


    @Inject
    public Context context;

    /**
     * Obtain a list of Users from service
     * @param userListener
     * @param client
     */
    @Override
    public void getUsersListFromService(final ListUsersInterface userListener, Retrofit client) {
        client.create(APIService.class).getUsers(ApiUtils.PROFILE_NAME_GITHUB, ApiUtils.PROFILE_REPOSITORY).enqueue(new Callback<List<UserEntity>>() {
            @Override
            public void onResponse(@NonNull Call<List<UserEntity>> call, @NonNull Response<List<UserEntity>> response) {
                if (response.isSuccessful()) {
                    userListener.UsersReady(response.body());
                } else {
                    userListener.UsersError(context.getString(R.string.communication_error_message));
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<UserEntity>> call, @NonNull Throwable t) {
                userListener.UsersError(context.getString(R.string.network_error_message));
            }
        });
    }


}
