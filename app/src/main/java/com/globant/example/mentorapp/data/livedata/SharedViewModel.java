package com.globant.example.mentorapp.data.livedata;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.globant.example.mentorapp.home.presentation.model.ModelUserEntity;
import com.globant.example.mentorapp.subscriberDetails.presentation.model.DetailsModel;
import com.globant.example.mentorapp.subscriberDetails.presentation.model.RepositoryModel;

import java.util.List;

/**
 * ViewModel Class to control data between activity, fragments and his states.
 * Created by oscar.amezquita on 9/06/2017.
 */

public class SharedViewModel extends ViewModel {

    private MutableLiveData<List<ModelUserEntity>> users = new MutableLiveData<>();

    private MutableLiveData<DetailsModel> userDetails = new MutableLiveData<>();

    private MutableLiveData<List<RepositoryModel>> userRepos = new MutableLiveData<>();

    public LiveData<List<ModelUserEntity>> getUsers() {
        return users;
    }

    public void setUsers(List<ModelUserEntity> userEntityList) {

        users.setValue(userEntityList);
    }

    public LiveData<DetailsModel> getUserDetails() {
        return userDetails;
    }

    public void setUsersDetails(DetailsModel userDetailsEntityList) {

        userDetails.setValue(userDetailsEntityList);
    }

    public MutableLiveData<List<RepositoryModel>> getUserRepos() {
        return userRepos;
    }

    public void setUserRepos(List<RepositoryModel> userRepos) {
        this.userRepos.setValue(userRepos);
    }

}
