package com.globant.example.mentorapp.mvp.base;

/**
 * Interface to define generic view basic behavior
 * Created by oscar.amezquita on 9/06/2017.
 */

public interface BaseView<T extends BaseModel> {

    /**
     * Method to organize UI actions in view layer
     */
    void render(T model);

}
