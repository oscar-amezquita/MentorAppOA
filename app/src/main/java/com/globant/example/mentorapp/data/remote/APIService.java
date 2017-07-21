package com.globant.example.mentorapp.data.remote;

import com.globant.example.mentorapp.home.domain.model.UserEntity;
import com.globant.example.mentorapp.subscriberDetails.domain.model.ListReposEntity;
import com.globant.example.mentorapp.subscriberDetails.domain.model.UserDetailEntity;

import java.util.List;

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

    @GET("/users/{userName}")
    Call<UserDetailEntity> getUserDetails(@Path("userName") String userName);

    @GET("/users/{userName}/repos")
    Call<List<ListReposEntity>> getUserRepos(@Path("userName") String userName);

}
