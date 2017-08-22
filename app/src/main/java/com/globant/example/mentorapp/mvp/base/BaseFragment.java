package com.globant.example.mentorapp.mvp.base;

import android.arch.lifecycle.LifecycleFragment;

import com.globant.example.mentorapp.R;
import com.globant.example.mentorapp.data.livedata.SharedViewModel;

/**
 * Generic class to define basic fragment behavior
 * Created by oscar.amezquita on 4/08/2017.
 */

public class BaseFragment extends LifecycleFragment {

    protected BaseActivity parent;
    protected SharedViewModel model;

    protected void showErrorMessage(BaseModel.ErrorResponse error) {
        switch (error) {
            case ERROR_CONNECTION:
                parent.simpleSnackBarMessage(getString(R.string.communication_error_message));
                break;
            case ERROR_RESPONSE:
                parent.simpleSnackBarMessage(getString(R.string.http_error_message));
                break;
        }
    }
}
