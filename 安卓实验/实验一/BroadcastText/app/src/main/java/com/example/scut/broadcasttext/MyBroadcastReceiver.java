package com.example.scut.broadcasttext;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by SCUT on 2018/4/4.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context,Intent intent){
        Toast.makeText(context,"received in MyBroadcastReceiver", Toast.LENGTH_SHORT).show();
    }
}
