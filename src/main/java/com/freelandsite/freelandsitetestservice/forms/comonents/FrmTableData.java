package com.freelandsite.freelandsitetestservice.forms.comonents;

import android.graphics.Typeface;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import com.freelandsite.freelandsitetestservice.R;
import com.freelandsite.freelandsitetestservice.freelabs.logs.FreeLogs;

import java.util.ArrayList;
import java.util.Random;

import ir.androidexception.datatable.DataTable;
import ir.androidexception.datatable.model.DataTableHeader;
import ir.androidexception.datatable.model.DataTableRow;

public class FrmTableData extends AppCompatActivity {
    private DataTable dataTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frmcomponents);
        loadTable();

    }



    private void loadTable(){
        FreeLogs.logInfo("FrmTableData","Load Data");
        FreeLogs.logError("FrmTableData","Load Data");
        dataTable = findViewById(R.id.data_table);

        DataTableHeader header = new DataTableHeader.Builder()
                .item("field name", 220)
                .item("field name", 70)
                .item("field name", 70)
                .item("field name", 70)

                .build();

        ArrayList<DataTableRow> rows = new ArrayList<>();
        // define 200 fake rows for table
        for(int i=0;i<200;i++) {
            Random r = new Random();
            int random = r.nextInt(i+1);
            int randomDiscount = r.nextInt(20);
            DataTableRow row = new DataTableRow.Builder()
                    .value("P" + i)
                    .value(String.valueOf(random))
                    .value(String.valueOf(random*1000).concat("$"))
                    .value(String.valueOf(randomDiscount).concat("%"))

                    .build();
            rows.add(row);
        }

        dataTable.setTypeface(Typeface.DEFAULT);
        dataTable.setHeader(header);
        dataTable.setRows(rows);
        dataTable.inflate(this);

    }
}