 /*
     @fazer
     implementar
  */

package br.banco.services.app.utils;
import android.content.Context;
import android.content.SharedPreferences;

 public class Preferences {


     public static final String LOGGED = "logged";

     private SharedPreferences mSharedPreferences;
     private Context mContext;

     public Preferences(Context aContex){
         mContext = aContex;
         mSharedPreferences = mContext.getSharedPreferences("settings", Context.MODE_PRIVATE);
     }

     public boolean getBoolean(String aKey){
         return mSharedPreferences.getBoolean(aKey, false);
     }

     public void setBoolean(String aKey, boolean aValue){
         mSharedPreferences.edit().putBoolean(aKey,aValue).commit();
     }
 }
