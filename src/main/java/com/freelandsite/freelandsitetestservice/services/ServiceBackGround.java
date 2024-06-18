package com.freelandsite.freelandsitetestservice.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

import com.freelandsite.freelandsitetestservice.MainActivity;
import com.freelandsite.freelandsitetestservice.workers.MyWorker;
import com.freelandsite.freelandsitetestservice.R;
import com.freelandsite.freelandsitetestservice.receibers.boot.RestartServiceBackground;

import java.util.UUID;

public class ServiceBackGround extends Service {


    private String CHANNEL_ID = "NOTIFICATION_CHANNEL";
    private int ID_MESSAGE=1001;

    private boolean IS_RUNNING;
    private static boolean IS_PAUSED;

    private Context ctx;

    public static int contapr=1;

    public ServiceBackGround() {
    }


    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
        sendMessage("Create Service");

    }



    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        IS_RUNNING=true;
        sendMessage("Start Command");
        WorkManager workManager = new WorkManager();
        workManager.start();
        return START_STICKY;
    }




    @Override
    public void onDestroy() {
        sendMessage("Destroy");
        IS_RUNNING=false;
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Intent broadcastIntent = new Intent(this, RestartServiceBackground.class);
        sendBroadcast(broadcastIntent);
        super.onDestroy();
    }



    private void createNotificationChannel() {
        ctx=this;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String appName = getString(R.string.app_name);
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    appName,
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }



    private void sendMessage(String message){
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent,  PendingIntent.FLAG_IMMUTABLE);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Service")
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentIntent(pendingIntent)
                .setColor(getResources().getColor(R.color.purple_500))
                .build();

        startForeground(ID_MESSAGE, notification);
        ID_MESSAGE++;
    }



    public static void  setPause(boolean paused){
        IS_PAUSED=paused;
    }


    public static boolean  isPause(){
        return IS_PAUSED;
    }


    class WorkManager extends Thread{

        @Override
        public void run(){
            while(IS_RUNNING){
                try {
                    Thread.sleep(5000);
                    if(IS_PAUSED==false) {
                        String UNIQUE_WORK_NAME = UUID.randomUUID().toString();
                        MyWorker MyWorker = new MyWorker(UNIQUE_WORK_NAME, ctx);
                    }

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            IS_RUNNING=false;
        }
    }



}