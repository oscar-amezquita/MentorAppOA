package com.globant.example.mentorapp.mvp.base;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.squareup.otto.Bus;

/**
 * Base class to define all methods common to any presenter.
 * Created by oscar.amezquita on 16/06/2017.
 */

public class BasePresenter {

    public Bus bus;

    public void onRegisterBus() {
        bus.register(this);
    }

    public void onUnregisterBus() {
        bus.unregister(this);
    }

}
