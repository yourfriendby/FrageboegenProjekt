package com.example.gentleman.i;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gentleman.i.addqu.*;
import com.example.gentleman.i.Server.*;

import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Choice_singleActivity extends AppCompatActivity {
    private ListView mListView;
    private Button mButton;
    private ListViewAdapter mAdapter;
    private List<ItemBean> mData;
    private EditText name_txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_single);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();

        TextView title=(TextView)findViewById(R.id.usual_ti);
        title.setText("单选题");
        mData=new ArrayList<ItemBean>();
        mAdapter=new ListViewAdapter(this,mData);
        mListView=(ListView)findViewById(R.id.list_single) ;
        mListView.setAdapter(mAdapter);
        name_txt=(EditText) findViewById(R.id.single_name);
        ImageButton add=(ImageButton)findViewById(R.id.add_single);
        final Button finish=(Button)findViewById(R.id.finish_single);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mData.add(new ItemBean());
                mAdapter.notifyDataSetChanged();
            }
        });
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=name_txt.getText().toString();
                if (name.isEmpty()){
                    Toast.makeText(Choice_singleActivity.this,"请输入题目标题",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mData.size()<2){
                    Toast.makeText(Choice_singleActivity.this,"请至少添加两个选项",Toast.LENGTH_SHORT).show();
                    return;
                }
                int fill=0;
                for (int i=0;i<mData.size();i++){
                    if (mData.get(i).getText()!=null){
                        fill++;
                    }
                }
                if (fill<2){
                    Toast.makeText(Choice_singleActivity.this,"请至少添加两个选项",Toast.LENGTH_SHORT).show();
                    return;
                }
                Survey survey=new Survey();
                survey.setQuesion(name);
                survey.setAns_num(fill);
                survey.setType("单选题");
                survey.save();
                int data_id= DataSupport.findLast(Survey.class).getId();
                for (int i=0;i<mData.size();i++){
                    if (mData.get(i).getText()!=null){
                        Answers answers=new Answers();
                        answers.setData_id(data_id);
                        answers.setAnswer(mData.get(i).getText());
                        answers.save();
                    }
                }
                finish();
            }
        });
    }
}
