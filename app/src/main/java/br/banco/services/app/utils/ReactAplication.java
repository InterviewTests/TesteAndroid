package br.banco.services.app.utils;

import android.util.Log;


public class ReactAplication {


    public void onCompleted(){

        try{

            Log.e("FUND", "onError: ");

        }catch (Exception ex){
            Log.d("FUND", "onCompleted: ERROR ONNEX: " + ex.getMessage() );
        }

    }

    public void onError(Throwable e){

        try{

            // StackTraceElement[] s = Thread.currentThread().getStackTrace();
            // StackTraceElement e = s[3];
            // String c = e.getClassName();
            // String m = e.getMethodName();

            String message = "-> CLASS: " + e.getStackTrace()[2].getClassName() +
                    " -> METHOD: " + e.getStackTrace()[2].getMethodName() +
                    "-> ERROR"+ e.getMessage() +
                    "";
            Log.e("FUND", "onError: " + message);

        }catch (Exception ex){
            Log.d("FUND", "onNext: ERROR ONNEX: " + ex.getMessage() );
        }

    }

    public void onNext(String message){

        try{

            StackTraceElement[] s = Thread.currentThread().getStackTrace();
            StackTraceElement e = s[3];

             if(e.getMethodName().equals("<init>")){ e = s[1]; }
            //if(e!=null){ e = s[2]; }

            String c = e.getClassName();
            String m = e.getMethodName();

            Log.d("FUND", "onNext: " + m + " -> " + message +" -> " + c );
            // Delegate.onError(String Message)

        }catch (Exception ex){
            Log.d("FUND", "onNext: ReactAplication ERROR: " + ex.getMessage() );
        }


    }
}

