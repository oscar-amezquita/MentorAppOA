package com.globant.example.mentorapp.domain;

import android.content.Context;
import android.support.annotation.NonNull;

import com.globant.example.mentorapp.R;
import com.globant.example.mentorapp.data.entity.UserEntity;
import com.globant.example.mentorapp.data.remote.APIService;
import com.globant.example.mentorapp.data.remote.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by oscar.amezquita on 7/06/2017.
 */

public class UserPresenterImpl implements UserPresenter {

    private final UserPresenterListener userListener;
    private final Context context;

    public UserPresenterImpl(UserPresenterListener userListener, Context context) {
        this.userListener = userListener;
        this.context = context;
    }

    @Override
    public void getUsersFromService() {

        ApiClient.getClient().create(APIService.class).getUsers().enqueue(new Callback<List<UserEntity>>() {
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
                userListener.UsersError(context.getString(R.string.communication_error_message));
            }
        });

    }

    public interface UserPresenterListener {
        void UsersReady(List<UserEntity> allUsers);

        void UsersError(String message);
    }
}
