package com.globant.example.mentorapp.presentation.di.Module;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.globant.example.mentorapp.MentorApplication;
import com.globant.example.mentorapp.data.remote.APIClient;
import com.globant.example.mentorapp.data.util.ApiUtils;
import com.globant.example.mentorapp.domain.interactor.ListUsersInteractorImpl;
import com.globant.example.mentorapp.presentation.model.SharedUserViewModel;
import com.globant.example.mentorapp.presentation.presenter.ListUsersPresenterImpl;
import com.globant.example.mentorapp.presentation.view.fragment.ListUsersFragment;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

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
public class ListUsersModule {

    public Context context;
    public MentorApplication application;

    public ListUsersModule(MentorApplication application) {
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
    protected ListUsersInteractorImpl provideUserInteractor() {
        return new ListUsersInteractorImpl(application.getUserComponent());
    }

    @Provides
    protected ListUsersFragment provideListUsersFragment() {
        return new ListUsersFragment();
    }

    @Provides
    @Singleton
    ListUsersPresenterImpl provideListUsersPresenter() {
        return new ListUsersPresenterImpl(application.getUserComponent());
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofitClient(OkHttpClient client) {
        return new Retrofit.Builder().client(client).baseUrl(ApiUtils.BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public APIClient providesApiClient(){
        return new APIClient(application.getUserComponent());
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
    Bus provideBus() {
        return new Bus(ThreadEnforcer.ANY);
    }

}
