package com.globant.example.mentorapp.mvp.base;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProviders;

import com.globant.example.mentorapp.data.livedata.SharedViewModel;

/**
 * Generic class to define basic fragment behavior
 * Created by oscar.amezquita on 4/08/2017.
 */

public class BaseFragment extends LifecycleFragment {

    protected BaseActivity parent;
    protected SharedViewModel model;

    public BaseFragment() {
        model = ViewModelProviders.of(this).get(SharedViewModel.class);
        parent = (BaseActivity) getActivity();
    }

    protected void showErrorMessage(String message) {
        parent.simpleSnackBarMessage(message);

    }
}
