package com.example.gentleman.i;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

public class LoginActivity extends AppCompatActivity {
    private Button loginbotton;
    private Button rebotton;
    private EditText inputuser;
    private EditText inputpass;
    private Handler handler=new Handler(){

        public void handleMessge(Message msg){
            if (msg.what==0){

            }
            if (msg.what==1){
                Toast.makeText(LoginActivity.this,"连接失败",Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginbotton=(Button)findViewById(R.id.login_botton);
        rebotton=(Button)findViewById(R.id.re_botton);
        inputpass=(EditText)findViewById(R.id.login_pass);
        inputuser=(EditText)findViewById(R.id.login_pass);
        login();
    }
    void login(){
        loginbotton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputuser.getText().toString().isEmpty()||inputpass.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this,"用户名或密码不能为空",Toast.LENGTH_SHORT).show();
                }
                else {
                    final JSONObject jsonObject=new JSONObject();
                    try {
                        jsonObject.put("username", URLEncoder.encode(inputuser.getText().toString(),"UTF-8"));
                        jsonObject.put("password", URLEncoder.encode(inputpass.getText().toString(),"UTF-8"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                URL url=new URL("http://47.94.102.120:8080/LoginServlet");
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
            }
        });
        rebotton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,reActivity.class);
                startActivity(intent);
            }
        });
    }
}
