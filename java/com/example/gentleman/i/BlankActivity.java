package com.example.gentleman.i;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.gentleman.i.Server.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BlankActivity extends AppCompatActivity implements View.OnClickListener {
    private List<fr> frList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blank);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        TextView title=(TextView)findViewById(R.id.usual_ti);
        title.setText("创建问题");
        frList=(List<fr>)getIntent().getSerializableExtra("new") ;
        Button button1=(Button)findViewById(R.id.choice_single);
        Button button2=(Button)findViewById(R.id.choice_more);
        Button button3=(Button)findViewById(R.id.fill);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.choice_single:
                startActivity(new Intent(BlankActivity.this,Choice_singleActivity.class));
                finish();
                break;
            case R.id.choice_more:
                startActivity(new Intent(BlankActivity.this,Choice_moreActivity.class));
                finish();
                break;
            case R.id.fill:
                startActivity(new Intent(BlankActivity.this,FillActivity.class));
                finish();
                break;
        }
    }
}
