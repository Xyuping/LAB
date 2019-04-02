package com.example.xyp.networktest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        WebView webview = (WebView)findViewById(R.id.webview);//打开的网页支持js
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient());//让网页在程序内部打开，不会自动跳转到系统默认浏览器
        webview.loadUrl("http://www.baidu.com");
    }
}
