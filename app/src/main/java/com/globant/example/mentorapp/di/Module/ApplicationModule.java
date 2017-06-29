package com.globant.example.mentorapp.di.Module;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import com.globant.example.mentorapp.MentorApplication;
import com.globant.example.mentorapp.home.domain.interactor.data.remote.APIService;
import com.globant.example.mentorapp.home.domain.interactor.data.remote.OkHttpConstants;
import com.globant.example.mentorapp.home.domain.interactor.data.util.ApiUtils;
import com.globant.example.mentorapp.home.domain.model.UserEntity;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

import android.arch.lifecycle.MutableLiveData;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class to control UserList injections
 * Created by oscar.amezquita on 8/06/2017.l
 */
@Module
public class ApplicationModule {

    public MentorApplication application;
    public Bus bus;

    public ApplicationModule(MentorApplication application) {

        this.application = application;
        bus = new Bus(ThreadEnforcer.ANY);
    }

    @Provides
    protected LinearLayoutManager provideLinearLayoutManager() {
        return new LinearLayoutManager(application);
    }

    @Provides
    @Singleton
    protected MentorApplication provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofitClient(OkHttpClient client) {
        return new Retrofit.Builder().client(client)
                .baseUrl(ApiUtils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(OkHttpConstants.READ_TIMEOUT, TimeUnit.MILLISECONDS);
        builder.writeTimeout(OkHttpConstants.WRITE_TIMEOUT, TimeUnit.MILLISECONDS);
        builder.addInterceptor(interceptor);
        return builder.build();

    }

    @Provides
    @Singleton
    public Bus provideBus() {
        return bus;
    }

    @Provides
    @Singleton
    public MutableLiveData<List<UserEntity>> provideLiveData() {
        return new MutableLiveData<>();
    }

    @Provides
    @Singleton
    DividerItemDecoration provideItemDecoration() {
        return new DividerItemDecoration(application, DividerItemDecoration.VERTICAL);
    }

    @Provides
    @Singleton
    APIService provideApiService(Retrofit client) {
        return client.create(APIService.class);
    }

}
