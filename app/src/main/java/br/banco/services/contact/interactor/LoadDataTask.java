 
package br.banco.services.contact.interactor;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;

import br.banco.services.app.utils.ReactAplication;
import br.banco.services.contact.Contact;
import br.banco.services.datasource.DataRepository;

public class LoadDataTask  extends AsyncTask<String, Void, String> {

    private WeakReference<Context> contextRef;
    public ILoadTask delegate = null;
    private DataRepository repository ;
    public  String APLICATION_FILE;
   // public Contact context;

    public ReactAplication RX = new ReactAplication();



    public LoadDataTask(Context c) {

        contextRef = new WeakReference<>(c);
        repository = new DataRepository(c);

        // delegate.processFinish(STATUS);
        // this.cancel(true);
        // RX.onNext("context sucesso " + (context!=null));

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


        // String result = null;

            URL url = new URL("https://floating-mountain-50292.herokuapp.com/fund.json");
            StringBuilder SB = new StringBuilder();

            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = null;

            while ((line = in.readLine()) != null) {

                SB.append(line);
                Log.d("FUND", "->" + line);

            }
            in.close();


          //  return result;








            /*


             result = APLICATION_FILE;
             Context context = contextRef.get();

             //RX.onNext("APLICATION_FILE = " + APLICATION_FILE );
            // RX.onNext("context = " + (context!=null) + " - contextRef=" + (contextRef!=null));

              repository.onLoad(context, APLICATION_FILE);

*/




        } catch (Exception e) {
            RX.onError(e);
            e.printStackTrace();
        }

        return result;

    }

    protected void onProgressUpdate(String... progress) { }

    @Override
    protected void onPostExecute(String result) {

        //RX.onNext("onPostExecute->" + result);
        delegate.processFinish(result);

    }









}
