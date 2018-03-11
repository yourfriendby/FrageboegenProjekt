package com.example.gentleman.i;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import java.util.Timer;
import java.util.TimerTask;

public class welcomeActivity extends AppCompatActivity {
    private Intent intent=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(welcomeActivity.this,LoginActivity.class));
                finish();
            }
        },1500);
    }
}
