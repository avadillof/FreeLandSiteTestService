package com.freelandsite.freelandsitetestservice.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.freelandsite.freelandsitetestservice.R;
import com.freelandsite.freelandsitetestservice.beans.BeanCotizaciones;


public class AdaptadorCotizaciones extends BaseAdapter {
    private Context context;
    public BeanCotizaciones[] ArrayBeanCotizaciones;

    public AdaptadorCotizaciones(Context context, BeanCotizaciones[] beanCotizaciones) {
        this.context = context;
        ArrayBeanCotizaciones = beanCotizaciones;
    }

    @Override
    public int getCount() {
        return ArrayBeanCotizaciones.length;
    }

    @Override
    public BeanCotizaciones getItem(int position) {
        return ArrayBeanCotizaciones[position];
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layoutcotizaciones, viewGroup, false);
        }


        TextView txtId = (TextView) view.findViewById(R.id.txtId);
        TextView txtName = (TextView) view.findViewById(R.id.txtName);
        TextView txtValue = (TextView) view.findViewById(R.id.txtValue);

        BeanCotizaciones beanCotizaciones = getItem(position);
        txtId.setText(String.valueOf(beanCotizaciones.getId()));
        txtName.setText(beanCotizaciones.getBanco());
        txtValue.setText(beanCotizaciones.getPercent());

        return view;
    }

}
