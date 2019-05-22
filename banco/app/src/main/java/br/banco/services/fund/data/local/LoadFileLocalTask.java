
package br.banco.services.fund.data.local;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class LoadFileLocalTask extends AsyncTask<String, String, String> {

    public ILoadFrom delegate = null;

    public LoadFileLocalTask(Context context) {

        //
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

       // Log.d("FUND","MODEL / "+getClass().getName()+" / doInBackground -> START ");
    }

    @Override
    protected String doInBackground(String... loadValues) {

      //  Log.d("FUND","doInBackground -> " + loadValues[0] );

        String teste = "0";
        String textValues = null;
        String urlServer = loadValues[0]; // ValidatorURL

        // web, local


        try {

            LoadFileLocal load = new LoadFileLocal();
            textValues = load.loadExternalJson(urlServer);

           Log.d("FUND","MODEL /"+getClass().getName()+" -> SUCCESS" );


        } catch (Exception e) {
            Log.d("FUND","MODEL /"+getClass().getName()+" doInBackground -> ERROR: " + e.getMessage());
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
