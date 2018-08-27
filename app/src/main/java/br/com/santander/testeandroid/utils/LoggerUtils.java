package br.com.santander.testeandroid.utils;

import android.util.Log;

public class LoggerUtils {
    private static final String TAG = "ST-LOG";

    public static final void println(String msg, Class calledClass) {
        Log.d(TAG, calledClass.getCanonicalName() + ":" + msg);
    }

}
