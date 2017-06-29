package com.globant.example.mentorapp.mvp.base;

/**
 * Generic interface to define basic activity behavior
 * Created by oscar.amezquita on 29/06/2017.
 */

public interface BaseActivity {

    /**
     * Provides SnackBar feature to deploy informative messages
     */
    void simpleSnackBarMessage(String message);
}
