package com.example.gentleman.i;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AdviceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        TextView title=(TextView)findViewById(R.id.usual_ti);
        title.setText("意见反馈");
    }
}
