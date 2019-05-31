 
package br.banco.services.datasource.remote;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.URL;

import br.banco.services.app.utils.ReactAplication;
 import br.banco.services.datasource.IDataSource;



public class RestTask extends AsyncTask<String, Void, String> {

    private WeakReference<Context> contextRef;
    public IDataSource delegate = null;
    public  String APLICATION_FILE;
    public ReactAplication RX = new ReactAplication();

    public RestTask(Context c) {

        contextRef = new WeakReference<>(c);
        RX.onNext("contextRef["+contextRef+"]");

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... loadValues) {

          APLICATION_FILE = loadValues[0];
          String result = null;

        try {

            URL url = new URL(APLICATION_FILE);
            StringBuilder SB = new StringBuilder();

           // Log.d("FUND", " @@@@ ->" + url);

            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = null;

            while ((line = in.readLine()) != null) {

                SB.append(line);
                //Log.d("FUND", "->" + line);

            }
            in.close();


        } catch (Exception e) {
            RX.onError(e);
            e.printStackTrace();
        }

        return result;

    }

    protected void onProgressUpdate(String... progress) { }

    @Override
    protected void onPostExecute(String result) {

        RX.onNext("________________________" + result);
        delegate.onNextTask(result);

    }




}
