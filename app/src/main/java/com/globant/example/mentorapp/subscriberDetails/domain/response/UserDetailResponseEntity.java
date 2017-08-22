package com.globant.example.mentorapp.subscriberDetails.domain.response;

import com.globant.example.mentorapp.data.remote.EventApiResponseEntity;
import com.globant.example.mentorapp.subscriberDetails.domain.model.UserDetailEntity;

/**
 * Class to manage otto responses from User details
 * Created by oscar.amezquita on 19/07/2017.
 */

public class UserDetailResponseEntity extends EventApiResponseEntity<UserDetailEntity> {

    private UserDetailResponseEntity(UserDetailResponseEntityBuilder builder) {
        super(builder);
    }

    public static class UserDetailResponseEntityBuilder extends EventApiResponseEntity.Builder {
        public EventApiResponseEntity build() {
            return new UserDetailResponseEntity(this);
        }
    }
}
