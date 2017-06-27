package com.globant.example.mentorapp.home.presentation.model;

/**
 * Represents a model of users information to show on the screen.
 * Created by oscar.amezquita on 16/06/2017.
 */

public class ModelUserEntity {

    private String name;
    private String imageUrl;
    private String profileUrl;

    public ModelUserEntity(String name, String imageUrl, String profileUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.profileUrl = profileUrl;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getProfileUrl() {
        return profileUrl;
    }
}
