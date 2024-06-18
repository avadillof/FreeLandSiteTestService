package com.freelandsite.freelandsitetestservice;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.freelandsite.freelandsitetestservice.forms.comonents.FrmTableData;
import com.freelandsite.freelandsitetestservice.forms.comonents.FrmViewPdf;
import com.freelandsite.freelandsitetestservice.receibers.ReceiverActionsBroadCast;
import com.freelandsite.freelandsitetestservice.services.Service;
import com.freelandsite.freelandsitetestservice.services.ServiceBackGround;
import com.tashila.pleasewait.PleaseWaitDialog;

import java.util.Date;


public class MainActivity extends AppCompatActivity {


    private ReceiverActionsBroadCast receiver;
    private ReceiverActionsBroadCast receiver1;

    private Activity activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity=this;
        setContentView(R.layout.mainactivity);

        receiver = new ReceiverActionsBroadCast();
        registerReceiver(receiver, new IntentFilter("SHOW_CONTADOR"));
        registerReceiver(receiver, new IntentFilter("ACTION_DIME_HOLA"));
        registerReceiver(receiver, new IntentFilter("LOAD_DATA"));


        Button btnSendBroadCastToService = findViewById(R.id.btnSendBroadcast);
        Button btnShowWaitt = findViewById(R.id.btnShowProgress);
        Button btnDataTable = findViewById(R.id.btnTableData);
        Button btnLoadPdf = findViewById(R.id.btnLoadPdf);




        startService(new Intent(this, ServiceBackGround.class));

        btnSendBroadCastToService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent msgIntent = new Intent(MainActivity.this, Service.class);
                msgIntent.setAction("ACTION_DIME_HOLA");
                msgIntent.putExtra("value", "Hola Pepe.." + new Date().toString());
                startService(msgIntent);
            }
        });


        btnShowWaitt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PleaseWaitDialog pleaseWaitDialog = new PleaseWaitDialog(activity);
                pleaseWaitDialog.setMessage("sasasas");
                pleaseWaitDialog.setTitle("asasasas");
                pleaseWaitDialog.setCancelable(false);
                pleaseWaitDialog.setIndeterminate(false);
                pleaseWaitDialog.setProgressStyle(PleaseWaitDialog.ProgressStyle.BOTH);
                pleaseWaitDialog.show();
            }
        });



        btnDataTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, FrmTableData.class);
                startActivity(intent);
            }
        });


        btnLoadPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, FrmViewPdf.class);
                intent.putExtra("url","https://ncu.rcnpv.com.tw/Uploads/20131231103232738561744.pdf");
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        // startService(new Intent(this, Service.class));
    }

}