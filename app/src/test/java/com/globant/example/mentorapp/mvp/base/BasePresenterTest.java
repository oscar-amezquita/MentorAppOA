package com.globant.example.mentorapp.mvp.base;

import com.squareup.otto.Bus;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

/**
 * Created by oscar.amezquita on 5/07/2017.
 */

public class BasePresenterTest {

    @Mock
    private BaseView view;
    @InjectMocks
    private BasePresenter presenter;
    @Mock
    private Bus bus;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter.attachView(view);
    }

    @Test
    public void verifyIsViewAttached_shouldSuccess() {
        Assert.assertTrue(presenter.isViewAttached());
    }

    @Test
    public void verifyIsViewDetached_shouldSuccess() {
        presenter.detachView();
        Assert.assertFalse(presenter.isViewAttached());
    }

    @Test
    public void verifyRegisterBus_ShouldSuccess() {
        presenter.registerBus();

        verify(bus).register(presenter);

    }

    @Test
    public void verifyUnRegisterBus_ShouldSuccess() {
        presenter.unregisterBus();

        verify(bus).unregister(presenter);
    }

}
