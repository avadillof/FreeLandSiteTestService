package com.freelandsite.freelandsitetestservice.tasks;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import com.freelandsite.freelandsitetestservice.beans.BeanCotizaciones;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class TaskMailProcces extends  Thread{
    private IntentService service;
    private boolean run=true;


    public static List<BeanCotizaciones> rowsCotizaciones = new ArrayList<BeanCotizaciones>();

    @Override
    public void run() {
        int conta = 0;
        while (isRun()) {
            try {
                conta++;
                Intent sendLevel = new Intent();
                sendLevel.setAction("SHOW_CONTADOR");
                sendLevel.putExtra("value", " " + conta);
                service.sendBroadcast(sendLevel);
                addData(conta);
                Thread.sleep(1000);

            } catch (InterruptedException e) {

                throw new RuntimeException(e);
            }
        }
    }

    private void addData(int i) {





                BeanCotizaciones beanCotizaciones = new BeanCotizaciones();
                beanCotizaciones.setId(i);
                beanCotizaciones.setBanco("Bankia "+ i );
                beanCotizaciones.setPercent("2"+i+"%");
                rowsCotizaciones.add(beanCotizaciones);



                Intent sendLevel = new Intent();
                sendLevel.setAction("LOAD_DATA");

                Bundle args = new Bundle();
                args.putSerializable("data",(Serializable)rowsCotizaciones);
                sendLevel.putExtra("ROWS",args);

                service.sendBroadcast(sendLevel);

    }


    public void setRun(boolean run) {
        this.run = run;
    }

    public boolean isRun() {
        return run;
    }


    public TaskMailProcces(IntentService service){
        this.service=service;
    }
}
