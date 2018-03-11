package com.example.gentleman.i;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.gentleman.i.Server.Answers;
import com.example.gentleman.i.Server.Survey;
import org.litepal.crud.DataSupport;
import java.util.List;
import com.example.gentleman.i.Server.*;
import com.mob.MobSDK;
import com.mob.commons.SHARESDK;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class PreviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        String name=getIntent().getStringExtra("title");
        TextView textView=(TextView)findViewById(R.id.title);
        textView.setText(name);
        List<fr>frlist=(List<fr>) getIntent().getSerializableExtra("data");
        LinearLayout main=(LinearLayout)findViewById(R.id.zbj);
        View []add_qu= new View[frlist.size()];
        for (int i=0;i<frlist.size();i++){
            add_qu[i]=LayoutInflater.from(this).inflate(R.layout.ques,null);
            TextView qu=(TextView)add_qu[i].findViewById(R.id.qu_txt);
            qu.setText(i+1+"、"+frlist.get(i).getQuestion()+"  （"+frlist.get(i).getType()+"）");
            main.addView(add_qu[i]);
            if (frlist.get(i).getType().equals("填空题")){
                View fill=LayoutInflater.from(this).inflate(R.layout.ans_fill,null);
                main.addView(fill);
            }
            else {
                View[]add_ans=new View[frlist.get(i).getAns_num()];
                for (int j=0;j<frlist.get(i).getAns_num();j++){
                    add_ans[j]=LayoutInflater.from(this).inflate(R.layout.ans,null);
                    TextView an=(TextView)add_ans[j].findViewById(R.id.ans_txt);
                    an.setText(frlist.get(i).getAnswer()[j]);
                    main.addView(add_ans[j]);
                }
            }
        }
        MobSDK.init(this,"2368094ca9aec"," 8fd798832faa54f5847551bba652870b");
        OnekeyShare os=new OnekeyShare();
        os.disableSSOWhenAuthorize();
        os.setImageUrl("http://firicon.fir.im/baa18a6d779c597888d685f1159070df5b4f2912");
        os.setTitleUrl("http://http://47.94.102.120:8080/");
        os.setText("问卷号****来填写吧");
        os.setTitle("问卷");
        os.show(this);
    }
}
