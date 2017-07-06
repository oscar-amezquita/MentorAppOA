package com.globant.example.mentorapp.home.domain.model;

import com.squareup.otto.Produce;

/**
 * Class to manage otto responses from ApiClient
 * Created by oscar.amezquita on 13/06/2017.
 */

public class EventApiResponseEntity<T> {

    public static final int HTTP_OK = 200;
    public static final int CONNECTION_ERROR = -1;
    private final int responseCode;
    private final String responseText;
    private final T data;

    private EventApiResponseEntity(Builder builder) {
        this.responseCode = builder.responseCode;
        this.responseText = builder.responseText;
        this.data = (T) builder.data;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getResponseText() {
        return responseText;
    }

    public T getData() {
        return data;
    }

    /**
     * {@code EventApiResponseEntity} builder static inner class.
     */
    public static final class Builder<T> {
        private int responseCode;
        private String responseText;
        private T data;

        @Produce
        public EventApiResponseEntity build() {
            return new EventApiResponseEntity(this);
        }

        /**
         * Sets the {@code responseCode} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param responseCode the {@code responseCode} to set
         * @return a reference to this Builder
         */
        public Builder withResponseCode(int responseCode) {
            this.responseCode = responseCode;
            return this;
        }

        /**
         * Sets the {@code responseText} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param responseText the {@code responseText} to set
         * @return a reference to this Builder
         */
        public Builder withResponseText(String responseText) {
            this.responseText = responseText;
            return this;
        }

        /**
         * Sets the {@code data} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param list the {@code data} to set
         * @return a reference to this Builder
         */
        public Builder withEntityList(T list) {
            this.data = list;
            return this;
        }
    }

}
