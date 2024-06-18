package com.freelandsite.freelandsitetestservice.receibers.boot;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.freelandsite.freelandsitetestservice.services.ServiceBackGround;

public class RestartServiceBackground extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        context.startService(new Intent(context, ServiceBackGround.class));
    }
}