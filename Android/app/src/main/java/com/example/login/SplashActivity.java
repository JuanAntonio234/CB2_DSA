package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;


public class SplashActivity extends AppCompatActivity {

    private Handler mHandler;
    private Runnable mRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        View view = this.getWindow().getDecorView();
        ((View) view).setBackgroundColor(0xff000000);
        mRunnable = new Runnable(){
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        };

        mHandler = new Handler();

        mHandler.postDelayed(mRunnable, 2000);
    }

    protected void onDestroy(){
        super.onDestroy();
        if(mHandler != null && mRunnable != null)
            mHandler.removeCallbacks(mRunnable);
    }
}