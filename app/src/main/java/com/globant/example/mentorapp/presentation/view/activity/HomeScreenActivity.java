package com.globant.example.mentorapp.presentation.view.activity;

import android.arch.lifecycle.LifecycleActivity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.widget.FrameLayout;

import com.globant.example.mentorapp.R;
import com.globant.example.mentorapp.presentation.view.fragment.ListUsersFragment;

public class HomeScreenActivity extends LifecycleActivity {

    private FrameLayout container;
    private final String LIST_TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        container = (FrameLayout) findViewById(R.id.fragment_container);
        if (savedInstanceState == null) {
            ListUsersFragment firstFragment = new ListUsersFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, firstFragment, LIST_TAG).commit();
        }

    }

    public void simpleSnackbarMessage(String message) {
        Snackbar.make(container, message, Snackbar.LENGTH_LONG)
                .show();
    }


}
