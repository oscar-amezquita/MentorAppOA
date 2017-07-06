package com.globant.example.mentorapp.mvp.base;

import com.squareup.otto.Bus;

/**
 * Base class to define all methods common to any presenter.
 * Created by oscar.amezquita on 16/06/2017.
 */

public class BasePresenter<T extends BaseView> {

    protected BaseView view;

    protected Bus bus;

    public BasePresenter(Bus bus) {
        this.bus = bus;
    }

    public void registerBus() {
        bus.register(this);
    }

    public void unregisterBus() {
        bus.unregister(this);
    }

    public void attachView(T view) {
        this.view = view;
    }

    public void detachView() {
        view = null;
    }

    protected boolean isViewAttached() {
        return view != null;
    }
}
