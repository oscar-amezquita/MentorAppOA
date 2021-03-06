package com.globant.example.mentorapp.subscriberDetails.domain.interactor;

import android.support.annotation.NonNull;

import com.globant.example.mentorapp.data.remote.APIService;
import com.globant.example.mentorapp.data.remote.EventApiResponseEntity;
import com.globant.example.mentorapp.data.util.ApiUtils;
import com.globant.example.mentorapp.mvp.base.BaseInteractor;
import com.globant.example.mentorapp.subscriberDetails.domain.model.RepoEntity;
import com.globant.example.mentorapp.subscriberDetails.domain.response.RepoListResponseEntity;
import com.squareup.otto.Bus;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This class runs gets repo list from  web service users queries.
 * Created by oscar.amezquita on 17/07/2017.
 */

public class FetchReposListInteractor extends BaseInteractor {

    @Inject
    public FetchReposListInteractor(APIService apiService, Bus bus) {
        super(apiService, bus);
    }

    /**
     * Obtain a list of repositories from the selected user, Response is send using otto using Asyncronous form.
     */
    public void execute(String userId) {
        final RepoListResponseEntity.RepoListResponseEntityBuilder builderEntity = new
                RepoListResponseEntity.RepoListResponseEntityBuilder();
        apiService.getUserRepos(userId).enqueue(new Callback<List<RepoEntity>>() {
            @Override
            public void onResponse(@NonNull Call<List<RepoEntity>> call, Response<List<RepoEntity>> response) {
                builderEntity.withResponseText(response.message());
                builderEntity.withResponseCode(response.code());
                if (response.isSuccessful()) {
                    builderEntity.withResponseCode(EventApiResponseEntity.HTTP_OK);
                    builderEntity.withEntityList(response.body());
                }
                bus.post(builderEntity.build());
            }

            @Override
            public void onFailure(Call<List<RepoEntity>> call, Throwable t) {
                builderEntity.withResponseText(ApiUtils.SERVICE_RESPONSE_COMM_ERROR);
                builderEntity.withResponseCode(EventApiResponseEntity.CONNECTION_ERROR);
                bus.post(builderEntity.build());
            }
        });
    }
}


