package com.globant.example.mentorapp.home.domain.model;

import java.util.List;

/**
 * Class to manage otto responses from ApiClient
 * Created by oscar.amezquita on 13/06/2017.
 */

public class EventApiResponseEntity {

    public static final int HTTP_OK = 200;
    public static final int CONNECTION_ERROR = -1;
    private int responseCode;
    private String responseText;
    private List<UserEntity> userEntityList;


    public EventApiResponseEntity(int responseCode, String responseText, List<UserEntity> userEntityList) {
        this.responseCode = responseCode;
        this.responseText = responseText;
        this.userEntityList = userEntityList;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

    public int getResponseCode() {

        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public List<UserEntity> getUserEntityList() {
        return userEntityList;
    }

    public void setUserEntityList(List<UserEntity> userEntityList) {
        this.userEntityList = userEntityList;
    }
}
