package com.globant.example.mentorapp.view.activity;

import java.util.Timer;
import java.util.TimerTask;

import com.globant.example.mentorapp.R;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {

            }
        };
        Timer timer = new Timer();
        timer.schedule(task, getResources().getInteger(R.integer.splash_screen_duration));
    }
}
