package com.globant.example.mentorapp;

import com.globant.example.mentorapp.home.domain.interactor.ListUsersInteractor;
import com.globant.example.mentorapp.home.domain.interactor.data.remote.APIService;
import com.globant.example.mentorapp.home.presentation.presenter.ListUsersPresenterImpl;
import com.squareup.otto.Bus;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testCreatePresenterInstace(){
        Bus bus= mock(Bus.class);
        APIService service = mock(APIService.class);
        ListUsersInteractor interactor = new ListUsersInteractor(service, bus);
        ListUsersPresenterImpl presenter = new ListUsersPresenterImpl(interactor, bus);

        Assert.assertNotNull(presenter);
    }
}