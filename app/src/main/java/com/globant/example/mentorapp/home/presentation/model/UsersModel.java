package com.globant.example.mentorapp.home.presentation.model;

import java.util.List;

/**
 * Created by oscar.amezquita on 16/06/2017.
 */

public class UsersModel {

    final List<ModelUserEntity> users;
    final errorResponse error;

    public UsersModel(List<ModelUserEntity> users, errorResponse error) {
        this.users = users;
        this.error = error;
    }

    public List<ModelUserEntity> getUsers() {
        return users;
    }

    public errorResponse getError() {
        return error;
    }

    public enum errorResponse{

        ERROR_RESPONSE,ERROR_CONNECTION

    }
}
