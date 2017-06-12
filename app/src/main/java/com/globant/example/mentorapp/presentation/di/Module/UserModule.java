package com.globant.example.mentorapp.presentation.di.Module;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.globant.example.mentorapp.MentorApplication;
import com.globant.example.mentorapp.data.remote.ApiUtils;
import com.globant.example.mentorapp.domain.interactor.ListUsersInteractorImpl;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by oscar.amezquita on 8/06/2017.
 */
@Module
public class UserModule {

    public Context context;
    public MentorApplication application;


    @Provides
    public Retrofit provideApiClient(OkHttpClient client) {
        return new Retrofit.Builder().client(client).baseUrl(ApiUtils.BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
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
    protected LinearLayoutManager provideLinearLayoutManager() {
        return new LinearLayoutManager(context);
    }

    @Provides
    public Context context() {
        return context;
    }

    @Provides
    @Singleton
    protected MentorApplication provideApplication() {
        return application;
    }

    @Provides
    protected ListUsersInteractorImpl provideUserInteractor() {
        return new ListUsersInteractorImpl();
    }

    public UserModule(Context context) {
        this.context = context;
        application = (MentorApplication) context.getApplicationContext();

    }


}
