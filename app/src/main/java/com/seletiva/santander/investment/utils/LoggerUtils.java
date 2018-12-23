package com.seletiva.santander.investment.utils;

import android.util.Log;

public class LoggerUtils {
    public static void Log(Class cls, String message) {
        final String tag = "[#] " + cls.getCanonicalName();
        Log.d(tag, message);
    }
}
