package com.globant.example.mentorapp.subscriberDetails.domain.interactor;

import android.support.annotation.NonNull;

import com.globant.example.mentorapp.data.remote.APIService;
import com.globant.example.mentorapp.data.remote.EventApiResponseEntity;
import com.globant.example.mentorapp.data.util.ApiUtils;
import com.globant.example.mentorapp.mvp.base.BaseInteractor;
import com.globant.example.mentorapp.subscriberDetails.domain.model.UserDetailEntity;
import com.globant.example.mentorapp.subscriberDetails.domain.response.UserDetailResponseEntity;
import com.squareup.otto.Bus;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This class runs gets user details from web service queries.
 * Created by oscar.amezquita on 14/07/2017.
 */

public class FetchUserDetailsInteractor extends BaseInteractor {

    @Inject
    public FetchUserDetailsInteractor(APIService apiService, Bus bus) {
        super(apiService, bus);
    }

    /**
     * Obtain a datail information about selected user from service, Response is send using otto using Asyncronous form.
     */
    public void execute(String id) {
        final UserDetailResponseEntity.UserDetailResponseEntityBuilder builderEntity = new
                UserDetailResponseEntity.UserDetailResponseEntityBuilder();
        apiService.getUserDetails(id).enqueue(new Callback<UserDetailEntity>() {
            @Override
            public void onResponse(@NonNull Call<UserDetailEntity> call, Response<UserDetailEntity> response) {
                builderEntity.withResponseText(response.message());
                builderEntity.withResponseCode(response.code());
                if (response.isSuccessful()) {
                    builderEntity.withResponseCode(EventApiResponseEntity.HTTP_OK);
                    builderEntity.withEntityList(response.body());
                }
                bus.post(builderEntity.build());
            }

            @Override
            public void onFailure(Call<UserDetailEntity> call, Throwable t) {
                builderEntity.withResponseText(ApiUtils.SERVICE_RESPONSE_COMM_ERROR);
                builderEntity.withResponseCode(EventApiResponseEntity.CONNECTION_ERROR);
                bus.post(builderEntity.build());
            }
        });
    }
}
