package com.example.gentleman.i;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gentleman.i.Network.*;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class reActivity extends AppCompatActivity {

    private Button submitbotton;
    private EditText inputuser;
    private EditText inputpass;
    private EditText inputpass_2;
    private Handler handler=new Handler(){

        public void handleMessge(Message msg){
            if (msg.what==0){

            }
            if (msg.what==1){
                Toast.makeText(reActivity.this,"连接失败",Toast.LENGTH_SHORT).show();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re);
        submitbotton=(Button)findViewById(R.id.submit_botton);
        inputpass=(EditText)findViewById(R.id.re_user);
        inputuser=(EditText)findViewById(R.id.re_pass);
        inputpass_2=(EditText)findViewById(R.id.re_pass_2);
        re();
    }

    private void re() {
        submitbotton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=inputuser.getText().toString();
                String pass1=inputpass.getText().toString();
                String pass2=inputpass_2.getText().toString();
                if (user.isEmpty()||pass1.isEmpty()||pass2.isEmpty()){
                    Toast.makeText(reActivity.this,"用户名或密码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!pass1.equals(pass2)){
                    Toast.makeText(reActivity.this,"两次输入不一致",Toast.LENGTH_SHORT).show();
                    return;
                }
                final JSONObject jsonObject=new JSONObject();
                try {
                    jsonObject.put("username", URLEncoder.encode(user,"UTF-8"));
                    jsonObject.put("password", URLEncoder.encode(pass1,"UTF-8"));
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            URL url=new URL("http://47.94.102.120:8080/RegisterServlet");
                            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                            conn.setConnectTimeout(5000);
                            conn.setReadTimeout(5000);
                            conn.setRequestMethod("POST");
                            conn.setDoOutput(true);
                            conn.getOutputStream().write(jsonObject.toString().getBytes());
                            if (conn.getResponseCode()==200){
                                InputStream is = conn.getInputStream();
                                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                byte[] buffer = new byte[1024];
                                int len = -1;
                                while ((len = is.read(buffer)) != -1) {
                                    baos.write(buffer, 0, len);

                                }
                                is.close();
                                Message msg=Message.obtain();
                                msg.what=0;
                                msg.obj=baos.toString();
                                handler.sendMessage(msg);
                            }
                            else {
                                Message msg=Message.obtain();
                                msg.what=1;
                                handler.sendMessage(msg);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }
}
