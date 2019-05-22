package br.banco.services.fund.interactor;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import br.banco.services.fund.domain.national.NationalFund;

public class ConvertGson {

    public ILoadTask delegate = null;
    public List<NationalFund> nacional = new ArrayList<NationalFund>();

    public ConvertGson(Context context) {
        // do here
    }

    public class ConvertOrigin extends AsyncTask<String, Void, NationalFund> {


        protected void onPreExecute() {

            Log.d("FUND", "onPreExecute -> START " );
        }

        @Override
        protected NationalFund doInBackground(String... strings) {

            String dataReceived = "" + strings[0];
            try {

                String jsDados = dataReceived;
                Gson GS = new Gson();

                return GS.fromJson(jsDados, NationalFund.class); // fromJson - toJson

            } catch (JsonSyntaxException e) {
                Log.d("FUND", "doInBackground -> ERRO " );
                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPostExecute(NationalFund response) {

             String message = "sucess";
             String content = "content here" ; // + response

            // delegate.convertFinish(content, message);
             Log.d("FUND", "onPostExecute -> SUCESS -> " + response );

        }

    }

}
