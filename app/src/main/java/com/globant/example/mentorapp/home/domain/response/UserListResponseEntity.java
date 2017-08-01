package com.globant.example.mentorapp.home.domain.response;

import com.globant.example.mentorapp.data.remote.EventApiResponseEntity;
import com.globant.example.mentorapp.home.domain.model.UserEntity;

import java.util.List;

/**
 * Class to manage otto responses from User List
 * Created by oscar.amezquita on 24/07/2017.
 */

public class UserListResponseEntity extends EventApiResponseEntity<List<UserEntity>> {

    protected UserListResponseEntity(Builder builder) {
        super(builder);
    }

    public static class UserListResponseEntityBuilder extends EventApiResponseEntity.Builder {
        public UserListResponseEntity build() {
            return new UserListResponseEntity(this);
        }
    }
}
