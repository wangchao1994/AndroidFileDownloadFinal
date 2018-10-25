package com.example.androidcustom2download.utils;

import android.util.Log;

public class LogUtils {

    public static final String TAG = "LogUtils";
    private static final boolean DEBUG = true;

    public static void d(String msg) {
        if (DEBUG)
            Log.d(TAG, msg);
    }

    public static void e(String msg) {
        if (DEBUG)
            Log.e(TAG, msg);
    }
}