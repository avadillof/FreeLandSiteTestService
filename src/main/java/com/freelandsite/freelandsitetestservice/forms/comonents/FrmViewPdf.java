package com.freelandsite.freelandsitetestservice.forms.comonents;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.print.PrintManager;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.freelandsite.freelandsitetestservice.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.gms.common.util.IOUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class FrmViewPdf extends AppCompatActivity  {



    private PDFView pdfview;
    private PrintManager printManager;

    private byte[] rawDocument;

    private  InputStream pdfStream;

    private String urlDocument;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();


        urlDocument=intent.getExtras().get("url").toString();

        setContentView(R.layout.activity_frm_view_pdf);
        this.setTitle("Print Document / Impresión Documento.");
        pdfview = findViewById(R.id.pdfView1);
        FloatingActionButton btnPrint = findViewById(R.id.btnPrint);


        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    try {

                        URL url = new URL(urlDocument);
                        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                        if (urlConnection.getResponseCode() == 200) {
                            pdfStream = new BufferedInputStream(urlConnection.getInputStream());
                            rawDocument = IOUtils.toByteArray(pdfStream);
                            pdfview.fromBytes(rawDocument).load();
                            pdfview.fitToWidth();
                            pdfview.loadPages();
                        }
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

        btnPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printPDF();
            }
        });




    }



    private void printPDF(){
        PrintManager printManager = (PrintManager)getSystemService(this.PRINT_SERVICE);
        try {

            PrintAttributes.Builder builder = new PrintAttributes.Builder();
            builder.setMediaSize( PrintAttributes.MediaSize.ISO_A4);
            builder.build();

            PrintDocumentAdapter printDocumentAdapter = new PdfDocumentAdapter(this);
            printManager.print("Document",printDocumentAdapter, new PrintAttributes.Builder().setMediaSize(PrintAttributes.MediaSize.ISO_A4).build());
        }catch (Exception ex){
            Toast.makeText(this, "Can't read pdf file", Toast.LENGTH_SHORT).show();
        }
    }

    class PdfDocumentAdapter extends PrintDocumentAdapter {

        Context context;


        public PdfDocumentAdapter(Context context){
            this.context = context;

        }
        @Override
        public void onLayout(PrintAttributes oldAttributes, PrintAttributes printAttributes1, CancellationSignal cancellationSignal, LayoutResultCallback layoutResultCallback, Bundle extras) {
            if (cancellationSignal.isCanceled())
                layoutResultCallback.onLayoutCancelled();

            else
            {
                PrintDocumentInfo.Builder builder = new PrintDocumentInfo.Builder("file name");
                builder.setContentType(PrintDocumentInfo.CONTENT_TYPE_DOCUMENT)
                        .setPageCount(PrintDocumentInfo.PAGE_COUNT_UNKNOWN)
                        .build();




                layoutResultCallback.onLayoutFinished(builder.build(),!printAttributes1.equals(printAttributes1));
            }
        }

        @Override
        public void onWrite(PageRange[] pages, ParcelFileDescriptor parcelFileDescriptor, CancellationSignal cancellationSignal, WriteResultCallback writeResultCallback) {

            OutputStream out = null;
            try{


                out = new FileOutputStream(parcelFileDescriptor.getFileDescriptor());

                byte[]buff = new byte[16384];
                int size;




                   out.write(rawDocument,0,rawDocument.length);

                if (cancellationSignal.isCanceled())
                    writeResultCallback.onWriteCancelled();
                else{
                    writeResultCallback.onWriteFinished(new PageRange[]{PageRange.ALL_PAGES});
                }
            } catch (Exception e) {
                writeResultCallback.onWriteFailed(e.getMessage());

                e.printStackTrace();
            }
            finally {
                try
                {

                    out.close();
                }catch (IOException ex){

                }
            }
        }
    }

}