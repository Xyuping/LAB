package com.example.xyp.networktest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class JsonActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

        textView = (TextView)findViewById(R.id.jsonText);
        Button jbutton = (Button)findViewById(R.id.json);
        jbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("");
                sendRequestWithOkHttpJson();
            }
        });

        Button gbutton=(Button)findViewById(R.id.gson);
        gbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("");
                sendRequestWithOkHttpGson();
            }
        });
    }

    void parseJSONWithJSONObject(String jsonData){
        try{
            JSONArray jsonArray = new JSONArray(jsonData);
            String text = "Json:\n";
            for(int i =0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String version = jsonObject.getString("version");
                text= text+"id:"+id+" name:"+name+" version:"+version+'\n';
            }
            showJsonData(text);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
    void parseJSONWithGSON(String jsonData){
        Gson gson = new Gson();
        List<App> appList = gson.fromJson(jsonData,new TypeToken<List<App>>(){}.getType());
        String text = "Gson:\n";
        for(App app:appList){
            text=text+"id:"+app.getId()+" name:"+app.getName()+" version:"+app.getVersion()+'\n';
        }
        showJsonData(text);
    }

    void sendRequestWithOkHttpJson(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client=new OkHttpClient();
                Request request = new Request.Builder().url("http://172.21.47.248/get_data.json").build();
                try{
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseJSONWithJSONObject(responseData);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    void sendRequestWithOkHttpGson(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client=new OkHttpClient();
                Request request = new Request.Builder().url("http://172.21.47.248/get_data.json").build();
                try{
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseJSONWithGSON(responseData);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    void showJsonData(final String response){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String s = textView.getText().toString()+response;
                textView.setText(s);
            }
        });
    }
}
