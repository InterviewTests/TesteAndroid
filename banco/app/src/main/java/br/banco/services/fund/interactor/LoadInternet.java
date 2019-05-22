package br.banco.services.fund.interactor;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import br.banco.services.app.utils.ConnectionChek;
import br.banco.services.app.utils.ReactAplication;

public class LoadInternet extends AsyncTask<String, String, String> {

    public ILoadTask delegate = null;
    public int ATTEMPTS = 0;
    public String STATUS = "error";
    public Context context;
    public ReactAplication RX = new ReactAplication();

    public LoadInternet(Context c) {
        this.ATTEMPTS = 3;
        this.context = c;

        if(context == null){
           // delegate.processFinish(STATUS);
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //this.cancel(true);
        // RX.onNext("Conectando..." );
    }

    @Override
    protected String doInBackground(String... loadValues) {

        String textValues = null;
        String urlServer = loadValues[0]; // ValidatorURL

        try {

            // temnta 3 vezes
            if(ConnectionChek.isNetworkAvailable(context) == true){
               // RX.onNext("Contectado com sucesso!");
                STATUS = "success";
            }else{
               // RX.onNext("Nao foi possivel conectar ");

                for(int count = 0; count < 3; count++){
                   // Thread.sleep(3000);
                    //RX.onNext("Tentando novamente:   " + count);
                    ATTEMPTS = count;

                    if(ConnectionChek.isNetworkAvailable(context) == true){
                        STATUS = "success";
                    }
                }//for
            }
        } catch (Exception e) {
            RX.onError(e);
            delegate.processFinish(STATUS);
            e.printStackTrace();

        }

        return STATUS;
    }

    protected void onProgressUpdate(String... progress) {
      //  RX.onNext("onProgressUpdate=" + progress[0]);
    }

    @Override
    protected void onPostExecute(String result) {
        //RX.onNext("onPostExecute=" + STATUS + "->result->"+result);
        delegate.processFinish(result);

    }

}
