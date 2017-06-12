package com.globant.example.mentorapp.data.remote;

import com.globant.example.mentorapp.data.entity.UserEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Interface used to describe the configured calls from GitHub service.
 * Created by oscar.amezquita on 7/06/2017.
 */

public interface APIService {

    @GET("/repos/{profileGitHub}/{RepoName}/subscribers")
    Call<List<UserEntity>> getUsers(@Path("profileGitHub") String userGit, @Path("RepoName") String repoName);

}
