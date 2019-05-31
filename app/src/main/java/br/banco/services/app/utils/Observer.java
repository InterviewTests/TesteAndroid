package br.banco.services.app.utils;

import android.util.Log;


public class Observer {

         public  Observer(){


         }


    public void onCompleted(){

        try{

            Log.e("FUND", "onError: ");

        }catch (Exception ex){
            Log.d("FUND", "onCompleted: ERROR ONNEX: " + ex.getMessage() );
        }

    }

    public void onError(Throwable e){

        try{


            String message = "-> CLASS: " + e.getStackTrace()[5].getClassName() +
                    " -> METHOD: " + e.getStackTrace()[5].getMethodName() +
                    "-> ERROR"+ e.getMessage() +
                    "";
            Log.e("FUND", "onError: " + message);

        }catch (Exception ex){
            Log.d("FUND", "onNext: ERROR onERROR: " + ex.getMessage() );
        }

    }

    public void onNext(String message){

        try{

            StackTraceElement[] s = Thread.currentThread().getStackTrace();
            StackTraceElement e = s[5];
            String levelApp = e.getMethodName();

            //if(e!=null){ e = s[2]; }

            String c = e.getClassName();
            String m = e.getMethodName();
            String f = e.getFileName();

            Log.d("FUND", "onNext: " + m + " -> " + message +" -> " + c );
            // Delegate.onError(String Message)

        }catch (Exception ex){
            Log.d("FUND", "onNext: ReactAplication ERROR: " + ex.getMessage() );
        }


    }



}

