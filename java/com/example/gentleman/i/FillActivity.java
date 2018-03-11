package com.example.gentleman.i;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gentleman.i.Server.Survey;

public class FillActivity extends AppCompatActivity {
    private EditText name_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        TextView title=(TextView)findViewById(R.id.usual_ti);
        title.setText("填空题");
        name_txt=(EditText)findViewById(R.id.fill_name);
        Button button=(Button)findViewById(R.id.finish_fill);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=name_txt.getText().toString();
                if (name.isEmpty()){
                    Toast.makeText(FillActivity.this,"请输入题目标题",Toast.LENGTH_SHORT).show();
                    return;}
                Survey survey=new Survey();
                survey.setQuesion(name);
                survey.setAns_num(0);
                survey.setType("填空题");
                survey.save();
                finish();
            }
        });
    }
}
