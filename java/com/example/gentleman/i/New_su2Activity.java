package com.example.gentleman.i;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gentleman.i.Server.*;

import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class New_su2Activity extends AppCompatActivity {
    private EditText su_name;
    private String name;
    private List<fr> frList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_su2);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){actionBar.hide();}

        Button button_preview=(Button)findViewById(R.id.preview);
        button_preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (su_name.getText().toString().isEmpty()){
                    Toast.makeText(New_su2Activity.this,"请输入调查名称",Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(New_su2Activity.this,"请先添加问题",Toast.LENGTH_SHORT).show();
            }
        });

        su_name=(EditText)findViewById(R.id.newsu_name_2);
        Intent intent=getIntent();
        name=intent.getStringExtra("su_name");
        su_name.setText(name);
        FloatingActionButton ib=(FloatingActionButton) findViewById(R.id.add);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_num=new Intent(New_su2Activity.this,BlankActivity.class);
                startActivity(intent_num);
            }
        });
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        final frAdapter adapter=new frAdapter(New_su2Activity.this,R.layout.item,frList);
        ListView listView=(ListView)findViewById(R.id.new_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, View view, final int position, final long id) {
                final fr f1=frList.get(position);
                    final String[]item={"上移","下移","删除"};
                    AlertDialog.Builder listdg=new AlertDialog.Builder(New_su2Activity.this);
                    listdg.setTitle("请选择");
                    listdg.setItems(item, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (which==0){
                                if (position>0){
                                    fr from=frList.get(position);
                                    fr to=frList.get(position-1);
                                    frList.remove(position);
                                    frList.remove(position-1);
                                    frList.add(position-1,from);
                                    frList.add(position,to);
                                    adapter.notifyDataSetChanged();
                                }
                            }
                            if (which==1){
                                if (position<frList.size()-1) {
                                    fr from=frList.get(position);
                                    fr to=frList.get(position+1);
                                    frList.remove(position+1);
                                    frList.add(position+1,from);
                                    frList.remove(position);
                                    frList.add(position,to);
                                    adapter.notifyDataSetChanged();
                                }
                            }
                            if (which==2){
                                Survey.delete(Survey.class,frList.get(position).getData_id());
                                frList.remove(position);adapter.notifyDataSetChanged();}
                        }
                    });listdg.show();

            }
        });
        int i=DataSupport.count(Survey.class);
        if (i>frList.size()){
            Survey survey=DataSupport.findLast(Survey.class);
            if (survey.getType().equals("填空题")!=true){
                int ans_num=survey.getAns_num();
                String ans[]=new String[ans_num];
                List<Answers>answerses=DataSupport.where("data_id=?", String.valueOf(survey.getId())).find(Answers.class);
                int posion=0;
                for (Answers answer:answerses){
                    ans[posion]=answer.getAnswer();
                    posion++;
                }
                fr f1=new fr(survey.getId(),survey.getAns_num(),survey.getQuesion(),survey.getType(),ans);
                frList.add(f1);
            }
            else {
                fr f1=new fr(survey.getId(),0,survey.getQuesion(),survey.getType(),null);
                frList.add(f1);
            }
        }
        Button button_preview=(Button)findViewById(R.id.preview);
        button_preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (su_name.getText().toString().isEmpty()){
                    Toast.makeText(New_su2Activity.this,"请输入调查名称",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (frList.size()==0){
                    Toast.makeText(New_su2Activity.this,"请先添加问题",Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent=new Intent(New_su2Activity.this,PreviewActivity.class);
                intent.putExtra("data",(Serializable) frList);
                intent.putExtra("title",su_name.getText().toString());
                startActivity(intent);
            }
        });
    }
}

