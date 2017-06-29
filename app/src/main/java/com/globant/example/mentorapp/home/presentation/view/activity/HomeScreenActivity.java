package com.globant.example.mentorapp.home.presentation.view.activity;

import com.globant.example.mentorapp.MentorApplication;
import com.globant.example.mentorapp.R;
import com.globant.example.mentorapp.home.presentation.view.fragment.ListUsersViewFragment;
import com.globant.example.mentorapp.mvp.base.BaseActivity;

import android.arch.lifecycle.LifecycleActivity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.widget.FrameLayout;

public class HomeScreenActivity extends LifecycleActivity implements BaseActivity {

    private FrameLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        container = (FrameLayout) findViewById(R.id.fragment_container);
        ((MentorApplication) getApplicationContext()).getApplicationComponent().inject(this);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new ListUsersViewFragment(), ListUsersViewFragment.LIST_TAG)
                    .commit();
        }
    }

    @Override
    public void simpleSnackBarMessage(String message) {
        Snackbar.make(container, message, Snackbar.LENGTH_LONG).show();
    }
}
