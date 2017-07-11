package com.globant.example.mentorapp.home.domain.interactor.data.remote;

import java.util.List;

import com.globant.example.mentorapp.home.domain.model.UserEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Interface used to describe the configured calls from GitHub service.
 * Created by oscar.amezquita on 7/06/2017.
 */

public interface APIService {

    @GET("/repos/{profileGitHub}/{repoName}/subscribers")
    Call<List<UserEntity>> getUsers(@Path("profileGitHub") String userGit, @Path("repoName") String repoName);
}
