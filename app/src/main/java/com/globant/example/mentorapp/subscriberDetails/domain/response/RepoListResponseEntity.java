package com.globant.example.mentorapp.subscriberDetails.domain.response;

import com.globant.example.mentorapp.data.remote.EventApiResponseEntity;
import com.globant.example.mentorapp.subscriberDetails.domain.model.RepoEntity;

import java.util.List;

/**
 * Class to manage otto responses from User details
 * Created by oscar.amezquita on 19/07/2017.
 */

public class RepoListResponseEntity extends EventApiResponseEntity<List<RepoEntity>> {

    private RepoListResponseEntity(Builder builder) {
        super(builder);
    }

    public static class RepoListResponseEntityBuilder extends EventApiResponseEntity.Builder {
        public RepoListResponseEntity build() {
            return new RepoListResponseEntity(this);
        }
    }

}

