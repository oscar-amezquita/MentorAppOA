package com.globant.example.mentorapp.com.globant.example.mentorapp.home.presentation.presenter;

import com.globant.example.mentorapp.home.domain.interactor.FetchUserListInteractor;
import com.globant.example.mentorapp.home.domain.model.EventApiResponseEntity;
import com.globant.example.mentorapp.home.domain.model.UserEntity;
import com.globant.example.mentorapp.home.presentation.model.ListUsersViewModel;
import com.globant.example.mentorapp.home.presentation.presenter.ListUsersPresenterImpl;
import com.globant.example.mentorapp.mvp.base.BaseModel;
import com.globant.example.mentorapp.mvp.base.BaseView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by oscar.amezquita on 5/07/2017.
 */

public class ListUsersPresenterImplTest {

    @Mock
    FetchUserListInteractor interactor;
    @Mock
    BaseView view;
    @InjectMocks
    ListUsersPresenterImpl presenter;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter.attachView(view);
    }

    @Test
    public void verifyGetUsersList_shouldSuccess() {
        presenter.getUsersList();
        verify(view).render(any(ListUsersViewModel.class));
        verify(interactor).execute();
    }

    @Test
    public void verifyGetUsersListWithoutView_shouldNotRender() {
        presenter.detachView();
        presenter.getUsersList();
        verify(view, never()).render(any(ListUsersViewModel.class));
        verify(interactor).execute();
    }

    @Test
    public void verifyControlResponse_withOkResponse_ShouldRenderUserList() {
        EventApiResponseEntity<List<UserEntity>> result = mock(EventApiResponseEntity.class);

        List<UserEntity> list = new ArrayList<>();

        when(result.getResponseCode()).thenReturn(EventApiResponseEntity.HTTP_OK);
        when(result.getData()).thenReturn(list);
        presenter.controlResponse(result);
        verify(view).render(any(ListUsersViewModel.class));

    }

    @Test
    public void verifyControlResponse_withConnectionErrorConnection_ShouldRenderMessage() {
        EventApiResponseEntity<List<UserEntity>> result = mock(EventApiResponseEntity.class);

        when(result.getResponseCode()).thenReturn(EventApiResponseEntity.CONNECTION_ERROR);

        presenter.controlResponse(result);
        verify(view).render(any(BaseModel.class));

    }

    @Test
    public void verifyControlResponse_withOtherErrorResponse_ShouldRenderMessage() {
        EventApiResponseEntity<List<UserEntity>> result = mock(EventApiResponseEntity.class);

        when(result.getResponseCode()).thenReturn(2);

        presenter.controlResponse(result);
        verify(view).render(any(BaseModel.class));

    }
}
