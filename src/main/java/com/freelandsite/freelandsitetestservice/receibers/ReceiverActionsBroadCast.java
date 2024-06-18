package com.freelandsite.freelandsitetestservice.receibers;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;
import com.freelandsite.freelandsitetestservice.R;
import com.freelandsite.freelandsitetestservice.adaptadores.AdaptadorCotizaciones;
import com.freelandsite.freelandsitetestservice.beans.BeanCotizaciones;
import java.util.List;


public class ReceiverActionsBroadCast extends BroadcastReceiver {


    public  AdaptadorCotizaciones adaptadorCotizaciones;

    public ReceiverActionsBroadCast() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.getAction().equals("SHOW_CONTADOR")){
            TextView textView = (TextView) ((Activity) context).findViewById(R.id.textView);
            textView.setText(intent.getStringExtra("value"));
        }

        if (intent.getAction().equals("ACTION_DIME_HOLA")) {
                TextView textView = (TextView) ((Activity) context).findViewById(R.id.textView);
                textView.setText(intent.getStringExtra("value"));
        }

        if (intent.getAction().equals("LOAD_DATA")) {
                    refreshTable(context, intent);
        }

    }

    private void refreshTable(Context context, Intent intent) {
        ((Activity) context).runOnUiThread(new Runnable(){
            public void run(){
                Bundle bundle = intent.getBundleExtra("ROWS");
                List<BeanCotizaciones> rowsCotizaciones= (List<BeanCotizaciones>) bundle.getSerializable("data");
                GridView tableData = ((Activity) context).findViewById(R.id.tableData);
                BeanCotizaciones[] rows = new BeanCotizaciones[rowsCotizaciones.size()];
                for(int i = 0;i<rowsCotizaciones.size();i++){
                    rows[i]=rowsCotizaciones.get(i);
                }
                if(adaptadorCotizaciones==null) {
                    adaptadorCotizaciones = new AdaptadorCotizaciones(context, rows);
                    tableData.setAdapter(adaptadorCotizaciones);
                }else{
                    adaptadorCotizaciones.ArrayBeanCotizaciones=rows;
                    adaptadorCotizaciones.notifyDataSetChanged();
                }
            }
        });
    }
}