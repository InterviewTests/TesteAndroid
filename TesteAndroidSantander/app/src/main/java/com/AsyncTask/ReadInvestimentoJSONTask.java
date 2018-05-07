package com.AsyncTask;

import android.os.AsyncTask;

import com.UI.InvestimentoFragment;
import com.adapters.Info;
import com.adapters.MoreInfo;
import com.http.HttpCall;
import com.http.HttpResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

    public class ReadInvestimentoJSONTask extends AsyncTask<String, Void, ArrayList<Object>> {

        private final InvestimentoFragment investimentoFragment;

        public ReadInvestimentoJSONTask(InvestimentoFragment investimentoFragment) {
            this.investimentoFragment = investimentoFragment;
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected ArrayList<Object> doInBackground(String... strings) {

            try {
                //Le a URL passada como argumento no médoto task.execute
                String url = strings[0];

                HttpCall http = new HttpCall(url);
                HttpResponse response = http.execute(HttpCall.Method.GET);

                //Objetos criados para auxiliar no busca das informacoes consumidas do JSON
                Map<String, String> textosIniciais = new HashMap<String, String>();
                ArrayList<Object> objetos = new ArrayList<Object>();
                try {
                    //Le as informações vindas do JSON
                    JSONObject screen = new JSONObject(response.extractDataAsString());
                    JSONObject screenJSON = screen.getJSONObject("screen");

                    investimentoFragment.preencheArrayMoreInfoJSON(textosIniciais, screenJSON);
                    investimentoFragment.preencheArrayInfoDownInfoJSON(screenJSON);

                    objetos.add(textosIniciais);
                    objetos.add(investimentoFragment.getMoreInfo());
                    objetos.add(investimentoFragment.getInfo());


                } catch (JSONException e) {
                    e.printStackTrace();

                }

                return objetos;

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<Object> objetos) {

            Map<String, String> textosIniciais = (Map<String, String>) objetos.get(0);
            List<MoreInfo> moreInfos = (List<MoreInfo>) objetos.get(1);
            List<Info> infos = (List<Info>) objetos.get(2);

            investimentoFragment.adicionaInformacoesIniciaisNaTela(textosIniciais);
            investimentoFragment.preencheRecyclerListViewComInfoMoreInfo(moreInfos, infos);

            super.onPostExecute(objetos);
        }
    }
