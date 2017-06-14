package com.globant.example.mentorapp.home.presentation.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

/**
 * ViewModel Class to control data between activity, fragments and his states.
 * Created by oscar.amezquita on 9/06/2017.
 */

public class SharedUserViewModel extends ViewModel {


    private MutableLiveData<List<ModelUserEntity>> users = new MutableLiveData<>();

    public LiveData<List<ModelUserEntity>> getUsers() {
        return users;
    }

    public void setUsers(List<ModelUserEntity> userEntityList) {

        users.setValue(userEntityList);
    }
}
