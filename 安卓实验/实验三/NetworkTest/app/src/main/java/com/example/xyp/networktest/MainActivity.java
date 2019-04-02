package com.example.xyp.networktest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button jbutton = (Button)findViewById(R.id.jumpToJson);
        Button wbutton = (Button)findViewById(R.id.jumpToWebview);
        Button xbutton = (Button)findViewById(R.id.jumpToXML);
        Button hbutton = (Button)findViewById(R.id.jumpToHttp);

        wbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,WebviewActivity.class);
                startActivity(intent);
            }
        });

        jbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,JsonActivity.class);
                startActivity(intent);
            }
        });

        hbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,HttpActivity.class);
                startActivity(intent);
            }
        });

        xbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,XMLActivity.class);
                startActivity(intent);
            }
        });
    }
}
