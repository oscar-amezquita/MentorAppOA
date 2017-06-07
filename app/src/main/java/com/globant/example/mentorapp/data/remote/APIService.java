package com.globant.example.mentorapp.data.remote;

import com.globant.example.mentorapp.data.entity.UserEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by oscar.amezquita on 7/06/2017.
 */

public interface APIService {

    @GET("/repos/googlesamples/android-architecture/subscribers")
    Call<List<UserEntity>> getUsers();
}
