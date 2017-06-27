package com.globant.example.mentorapp.home.presentation.view.activity;

import android.arch.lifecycle.LifecycleActivity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;

import com.globant.example.mentorapp.MentorApplication;
import com.globant.example.mentorapp.R;
import com.globant.example.mentorapp.home.presentation.view.fragment.ListUsersViewFragment;

public class HomeScreenActivity extends LifecycleActivity {

    private static FrameLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        container = (FrameLayout) findViewById(R.id.fragment_container);
        ((MentorApplication) getApplicationContext()).getApplicationComponent().inject(this);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, (Fragment) new ListUsersViewFragment(), ListUsersViewFragment.LIST_TAG).commit();
        }
    }

    public static void simpleSnackBarMessage(String message) {
        Snackbar.make(container, message, Snackbar.LENGTH_LONG)
                .show();
    }
}
