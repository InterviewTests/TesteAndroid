package br.banco.services.app.utils;

import android.util.Log;

public final class Layout {


    public void showLog(String tag, String event){

        Log.d(tag, " -> "+getClass().getName()+" -> " + event) ;
    }


}
