 /*
     @fazer
     implementar @ import android.net.NetworkCapabilities;
     cehcar versao do android suporta este
  */

package br.banco.services.app.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

 public class Connection {

     private static int TYPE_WIFI = 1;
     private static int TYPE_MOBILE = 2;
     private static int TYPE_NOT_CONNECTED = 0;

     public static int getConnectivityStatus(Context context) {

         ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
         NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

         switch (activeNetwork.getSubtype())
         {

             case  ConnectivityManager.TYPE_WIFI:
                 return TYPE_WIFI;
             case  ConnectivityManager.TYPE_MOBILE:
                 return TYPE_MOBILE;
             default:
                 return TYPE_NOT_CONNECTED;

         }
     }
 }
