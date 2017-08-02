package com.globant.example.mentorapp.mvp.base;

import com.globant.example.mentorapp.data.remote.APIService;
import com.squareup.otto.Bus;

/**
 * Class to manage general behavior from all interactors
 * Created by oscar.amezquita on 1/08/2017.
 */

public class BaseInteractor {
    protected final APIService apiService;
    protected final Bus bus;

    public BaseInteractor(APIService apiService, Bus bus) {
        this.apiService = apiService;
        this.bus = bus;
    }
}
