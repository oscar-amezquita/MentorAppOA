package com.globant.example.mentorapp.subscriberDetails.presentation.model;

/**
 * Represents a model of users detail information to show on the screen.
 * Created by oscar.amezquita on 17/07/2017.
 */

public class UserDetailsModel {

    private String imageUrl;
    private String userName;
    private String location;
    private String company;
    private int followers;
    private int following;

    private UserDetailsModel(Builder builder) {
        userName = builder.userName;
        location = builder.location;
        company = builder.company;
        followers = builder.followers;
        following = builder.following;
        imageUrl = builder.imageUrl;
    }

    public String getUserName() {
        return userName;
    }

    public String getLocation() {
        return location;
    }

    public String getCompany() {
        return company;
    }

    public int getFollowers() {
        return followers;
    }

    public int getFollowing() {
        return following;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * {@code UserDetailsModel} builder static inner class.
     */
    public static final class Builder {
        private String imageUrl;
        private String userName;
        private String location;
        private String company;
        private int followers;
        private int following;

        public Builder() {
        }

        /**
         * Sets the {@code userName} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param userName the {@code userName} to set
         * @return a reference to this Builder
         */
        public Builder withUserName(String userName) {
            this.userName = userName;
            return this;
        }

        /**
         * Sets the {@code location} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param location the {@code location} to set
         * @return a reference to this Builder
         */
        public Builder withLocation(String location) {
            this.location = location;
            return this;
        }

        /**
         * Sets the {@code company} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param company the {@code company} to set
         * @return a reference to this Builder
         */
        public Builder withCompany(String company) {
            this.company = company;
            return this;
        }

        /**
         * Sets the {@code followers} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param followers the {@code followers} to set
         * @return a reference to this Builder
         */
        public Builder withFolllowers(int followers) {
            this.followers = followers;
            return this;
        }

        /**
         * Sets the {@code following} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param following the {@code following} to set
         * @return a reference to this Builder
         */
        public Builder withFollowing(int following) {
            this.following = following;
            return this;
        }

        /**
         * Sets the {@code imageUrl} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param imageUrl the {@code imageUrl} to set
         * @return a reference to this Builder
         */
        public Builder withImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        /**
         * Returns a {@code UserDetailsModel} built from the parameters previously set.
         *
         * @return a {@code UserDetailsModel} built with parameters of this {@code UserDetailsModel.Builder}
         */
        public UserDetailsModel build() {
            return new UserDetailsModel(this);
        }
    }

}
