package com.globant.example.mentorapp.di.Module;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;

import com.globant.example.mentorapp.MentorApplication;
import com.globant.example.mentorapp.home.domain.interactor.ListUsersInteractor;
import com.globant.example.mentorapp.home.domain.interactor.ListUsersInteractorImpl;
import com.globant.example.mentorapp.home.domain.interactor.data.remote.APIClient;
import com.globant.example.mentorapp.home.domain.interactor.data.util.ApiUtils;
import com.globant.example.mentorapp.home.domain.model.EventApiResponseEntity;
import com.globant.example.mentorapp.home.domain.model.UserEntity;
import com.globant.example.mentorapp.home.presentation.presenter.ListUsersPresenter;
import com.globant.example.mentorapp.home.presentation.presenter.ListUsersPresenterImpl;
import com.globant.example.mentorapp.home.presentation.view.fragment.ListUsersFragment;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class to control UserList injections
 * Created by oscar.amezquita on 8/06/2017.
 */
@Module
public class ApplicationModule {

    public Context context;
    public MentorApplication application;

    public ApplicationModule(MentorApplication application) {
        this.context = application;
        this.application = application;
    }

    @Provides
    protected LinearLayoutManager provideLinearLayoutManager() {
        return new LinearLayoutManager(context);
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    protected MentorApplication provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    public ListUsersInteractor provideUserInteractor() {
        return new ListUsersInteractorImpl(providesApiClient());
    }

    @Provides
    protected ListUsersFragment provideListUsersFragment() {
        return new ListUsersFragment();
    }

    @Provides
    @Singleton
    ListUsersPresenter provideListUsersPresenter() {
        return new ListUsersPresenterImpl(application.getApplicationComponent());

    }

    @Provides
    @Singleton
    public Retrofit provideRetrofitClient(OkHttpClient client) {
        return new Retrofit.Builder().client(client).baseUrl(ApiUtils.BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public APIClient providesApiClient() {
        return new APIClient(application.getApplicationComponent());
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder b = new OkHttpClient.Builder();
        b.readTimeout(2000, TimeUnit.MILLISECONDS);
        b.writeTimeout(4000, TimeUnit.MILLISECONDS);
        b.addInterceptor(interceptor);
        return b.build();

    }

    @Provides
    @Singleton
    public Bus provideBus() {
        return new Bus(ThreadEnforcer.ANY);
    }

    @Provides
    @Singleton
    public MutableLiveData<List<UserEntity>> provideLiveData() {
        return new MutableLiveData<>();
    }

    @Provides
    @Singleton
    DividerItemDecoration provideItemDecoration() {
        return new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
    }

    @Provides
    @Singleton
    public EventApiResponseEntity provideResponseEntity() {
        return new EventApiResponseEntity(0, "", null);
    }
}
