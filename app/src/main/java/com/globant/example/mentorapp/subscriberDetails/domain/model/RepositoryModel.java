package com.globant.example.mentorapp.subscriberDetails.domain.model;

/**
 * Represents a model of repository basic information.
 * Created by oscar.amezquita on 12/07/2017.
 */
public class RepositoryModel {

    private final String repoName;
    private final String repoURL;

    public RepositoryModel(String repoName, String repoURL) {
        this.repoName = repoName;
        this.repoURL = repoURL;
    }

    public String getRepoName() {
        return repoName;
    }

    public String getRepoURL() {
        return repoURL;
    }
}
