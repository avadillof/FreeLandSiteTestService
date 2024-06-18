package com.freelandsite.freelandsitetestservice.freelabs.logs;


import android.util.Log;

public class FreeLogs {

    public static void logInfo(String TAG,String message){
        Log.i(TAG, message);
    }

    public static void logError(String TAG,String message){
        Log.e(TAG, message);
    }
}
