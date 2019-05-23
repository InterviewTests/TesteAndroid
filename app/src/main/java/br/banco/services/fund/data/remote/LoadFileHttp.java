package br.banco.services.fund.data.remote;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoadFileHttp {


    public String loadExternalJson(String jsonURL) throws IOException {

      //  Log.d("FUND","MODEL / "+getClass().getName()+" -> " + jsonURL);

        try{

            URL url = new URL(jsonURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder SB = new StringBuilder();

            while ((line = in.readLine()) != null) {

               // Log.d("FUND", "@@ loadJSON() " + line);

                SB.append(line);

            }

            //Log.d("FUND","MODEL / loadExternalJson -> SUCCESS" );

            in.close();
            return SB.toString();

        }catch (Exception ex){
            Log.d("FUND","MODEL / loadExternalJson -> ERROR" +ex.getMessage() );
            return  null;
        }



    } //loadExternalJson



}




