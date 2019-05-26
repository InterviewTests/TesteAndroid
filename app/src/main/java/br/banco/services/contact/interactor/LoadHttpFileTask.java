 
package br.banco.services.contact.interactor;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import br.banco.services.contact.interactor.ILoadTask;
import br.banco.services.contact.interactor.LoadHttpFile;

public class LoadHttpFileTask extends AsyncTask<String, String, String> {

    public ILoadTask delegate = null;

    public LoadHttpFileTask(Context context) {

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... loadValues) {

        String teste = "0";
        String textValues = null;
        String urlServer = loadValues[0]; // ValidatorURL


        try {

            //FilelTask load = new FilelTask();
           // textValues = load.loadExternalJson(urlServer);

          //  Log.d("FUND","MODEL / doInBackground -> SUCCESS" );


        } catch (Exception e) {
            Log.d("FUND","MODEL / doInBackground -> ERROR: " + e.getMessage());
            e.printStackTrace();
        }

        return textValues;

    }

    protected void onProgressUpdate(String... progress) {
        //Log.d("FUND", "MODEL / doInBackground -> " + progress[0]);
    }

    @Override
    protected void onPostExecute(String result) {

       // Log.d("FUND", "MODEL / onPostExecute -> result=" + result);
        delegate.processFinish(result);

    }









}
