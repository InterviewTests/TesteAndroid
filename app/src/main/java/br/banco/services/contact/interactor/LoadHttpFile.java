package br.banco.services.contact.interactor;
import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import br.banco.services.app.utils.ReactAplication;

public class LoadHttpFile implements ILoadTask.IFileRemote {

    ReactAplication RX = new ReactAplication();

    public LoadHttpFile() {

    }

    public  String onLoad(Context c, String URLstr) {

        String fileStr = null;

        try {
            URL url = new URL(URLstr);
            StringBuilder SB = new StringBuilder();
            String line;

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            while ((line = in.readLine()) != null) {
                SB.append(line);
                Log.d("FUND", "->" + line);
            }

            in.close();
            //  fileStr = SB;toString();
           return SB.toString();

        } catch (IOException io) {
            RX.onError(io);
        } catch (Exception e) {
            RX.onError(e);
        }

        return fileStr;
    }




}




