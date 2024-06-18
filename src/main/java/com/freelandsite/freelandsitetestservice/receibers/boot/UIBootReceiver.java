package com.freelandsite.freelandsitetestservice.receibers.boot;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.freelandsite.freelandsitetestservice.services.Service;
import com.freelandsite.freelandsitetestservice.services.ServiceBackGround;

public class UIBootReceiver extends BroadcastReceiver  {

    @Override
    public void onReceive(Context context, Intent arg1) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(arg1.getAction())) {
            Intent serviceIntent = new Intent(context, Service.class);
            context.startService(serviceIntent);

            context.startService(new Intent(context, ServiceBackGround.class));
        }

    }

}
