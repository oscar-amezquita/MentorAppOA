package com.globant.example.mentorapp.presentation.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.globant.example.mentorapp.data.entity.UserEntity;

import java.util.List;

/**
 * Created by oscar.amezquita on 9/06/2017.
 */

public class SharedUserViewModel extends ViewModel {


    private MutableLiveData<List<UserEntity>> users = new MutableLiveData<>();

    public LiveData<List<UserEntity>> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> userEntityList) {

        users.setValue(userEntityList);
    }
}
