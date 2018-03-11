package com.example.gentleman.i;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import org.litepal.tablemanager.Connector;

public class MamagerActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mamager);
        Connector.getDatabase();
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){actionBar.hide();}
        Button button_new_su=(Button)findViewById(R.id.new_survey);
        ImageButton button_advice=(ImageButton)findViewById(R.id.advice);
        ImageButton button_recycle=(ImageButton)findViewById(R.id.recycle);
        button_new_su.setOnClickListener(this);
        button_advice.setOnClickListener(this);
        button_recycle.setOnClickListener(this);
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.new_survey:
                startActivity(new Intent(MamagerActivity.this,New_suActivity.class));
            break;
            case R.id.advice:
                startActivity(new Intent(MamagerActivity.this,AdviceActivity.class));
            break;
            case R.id.recycle:
                startActivity(new Intent(MamagerActivity.this,RecycleActivity.class));
            break;
        }
    }
}
