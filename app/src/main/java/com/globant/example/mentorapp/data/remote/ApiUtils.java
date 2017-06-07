package com.globant.example.mentorapp.data.remote;

/**
 * Created by oscar.amezquita on 7/06/2017.
 */

public class ApiUtils {

    private ApiUtils() {
    }

    public static final String BASE_URL = "https://api.github.com";

    public static APIService getAPIService() {

        return ApiClient.getClient().create(APIService.class);
    }
}
