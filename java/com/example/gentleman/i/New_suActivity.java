package com.example.gentleman.i;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gentleman.i.Server.Answers;
import com.example.gentleman.i.Server.Survey;

import org.litepal.crud.DataSupport;

public class New_suActivity extends AppCompatActivity {
    private EditText su_name;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_su);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){actionBar.hide();}
        DataSupport.deleteAll(Survey.class);
        DataSupport.deleteAll(Answers.class);
        su_name=(EditText)findViewById(R.id.newsu_name_1);
        Button button=(Button)findViewById(R.id.newsu_botton_1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=su_name.getText().toString();
                if (name.isEmpty()){
                    Toast.makeText(New_suActivity.this,"请输入调查名称",Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent=new Intent(New_suActivity.this,New_su2Activity.class);
                intent.putExtra("su_name",name);
                startActivity(intent);
                finish();
            }
        });
    }
}
