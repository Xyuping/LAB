package com.example.xyp.networktest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);

        textView = (TextView) findViewById(R.id.responseText);

        Button ugbutton = (Button) findViewById(R.id.HttpURLConnectionGet);
        ugbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRequestWithHttpURLConnection();
            }
        });

        Button ogbutton = (Button)findViewById(R.id.okHttpGet);
        ogbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendRequestWithOkHttp();

            }
        });

        Button upbutton = (Button) findViewById(R.id.HttpURLConnectionPost);
        upbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Button opbutton = (Button)findViewById(R.id.okHttpPost);
        opbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    void sendRequestWithHttpURLConnection(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try{
                    URL url = new URL("https:www.baidu.com");
                    connection=(HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream inputStream = connection.getInputStream();

                    reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while((line = reader.readLine())!=null){
                        stringBuilder.append(line);
                    }
                    showResponse(stringBuilder.toString());
                }catch (MalformedURLException e){
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }finally {
                    if(reader!=null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if(connection!=null)
                        connection.disconnect();
                }
            }
        }).start();
    }
    void  showResponse(final String response){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText(response);
            }
        });
    }

    void sendRequestWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url("http://www.baidu.com").build();
                try{
                    Response response = client.newCall(request).execute();
                    String responseText = response.body().string();
                    showResponse(responseText);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
