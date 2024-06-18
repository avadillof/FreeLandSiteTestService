package com.freelandsite.freelandsitetestservice.workers;


import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.freelandsite.freelandsitetestservice.services.MyService;
import com.freelandsite.freelandsitetestservice.services.ServiceBackGround;

public class MyWorker extends Thread {
    private final Context context;


    public MyWorker(String name,Context context) {
        super(name);
        this.context = context;
        this.start();
    }

    @Override
    public void run()
    {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, "Process Running "+ServiceBackGround.contapr, Toast.LENGTH_SHORT).show();
                ServiceBackGround.contapr++;
            }
        });

    }

}