package com.globant.example.mentorapp.mvp.base;

import com.squareup.otto.Bus;

/**
 * Base class to define all methods common to any presenter.
 * Created by oscar.amezquita on 16/06/2017.
 */

public abstract class BasePresenter<T extends BaseView> {

    protected BaseView baseView;

    protected Bus bus;

    public void onRegisterBus() {
        bus.register(this);
    }

    public void onUnregisterBus() {
        bus.unregister(this);
    }

    public void attachView(T view) {
        baseView = view;
    }

    public void detachView() {
        baseView = null;
    }

    protected boolean isViewAttached() {
        return baseView != null;
    }
}
