package com.freelandsite.freelandsitetestservice.services;

import android.app.IntentService;
import android.content.Intent;

import com.freelandsite.freelandsitetestservice.adaptadores.AdaptadorCotizaciones;
import com.freelandsite.freelandsitetestservice.tasks.TaskMailProcces;

public class Service extends IntentService {





    private int conta = 1;
    public static TaskMailProcces processMain;
    public Service() {
        super("Service");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
            String action = intent.getAction();
            String value = intent.getStringExtra("value");
            if(action==null){
                mainProcessService();
            }else {
                if (action.equals("ACTION_DIME_HOLA")) {
                    Intent bcIntent = new Intent();
                    bcIntent.setAction("ACTION_DIME_HOLA");
                    bcIntent.putExtra("value", value);
                    sendBroadcast(bcIntent);
                }
            }
    }
    private void mainProcessService() {
        if (processMain == null || !processMain.isAlive()) {
            processMain= new TaskMailProcces(this);
            processMain.start();
        }
    }
}