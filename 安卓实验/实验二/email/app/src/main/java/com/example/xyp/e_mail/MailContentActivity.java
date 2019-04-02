package com.example.xyp.e_mail;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MailContentActivity extends AppCompatActivity {

    public static void actionStart(Context context,String mailTitle,String sender,String mailContent){
        Intent intent = new Intent(context,MailContentActivity.class);
        intent.putExtra("mail_title",mailTitle);
        intent.putExtra("sender",sender);
        intent.putExtra("mail_content",mailContent);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_content);
        String mailTitle = getIntent().getStringExtra("mail_title");
        String sender = getIntent().getStringExtra("mail_sender");
        String mailContent = getIntent().getStringExtra("mail_content");
        MailContentFragment mailContentFragment = (MailContentFragment) getSupportFragmentManager().findFragmentById(R.id.mail_content_fragment);
        mailContentFragment.refresh(mailTitle,sender,mailContent);
    }
}
