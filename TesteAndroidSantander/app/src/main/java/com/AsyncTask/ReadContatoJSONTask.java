package com.AsyncTask;

import android.os.AsyncTask;

import com.UI.FormularioFragment;
import com.entities.Componente;
import com.http.HttpCall;
import com.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadContatoJSONTask extends AsyncTask<String, Void, List<Componente>> {

    private final FormularioFragment formularioFragment;

    public ReadContatoJSONTask(FormularioFragment formularioFragment) {
        this.formularioFragment = formularioFragment;
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected List<Componente> doInBackground(String... strings) {

        try {
            //Le a URL passada como argumento no médoto task.execute
            String url = strings[0];
            HttpCall http = new HttpCall(url);
            HttpResponse response = http.execute(HttpCall.Method.GET);

            //Objetos criados para auxiliar no busca das informacoes consumidas do JSON
            formularioFragment.setComponentes(new ArrayList<Componente>());

            try {
                //Le as informações vindas do JSON
                JSONObject cells = new JSONObject(response.extractDataAsString());
                JSONArray arraysCells = cells.getJSONArray("cells");

                //preenche e cria os objetos de acordo com informacoes da JSON
                for (int i = 0; i < arraysCells.length(); i++) {
                    Componente p = new Componente();
                    JSONObject cell = arraysCells.getJSONObject(i);

                    formularioFragment.criaObjetosComponente(formularioFragment.getComponentes(), p, cell);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return formularioFragment.getComponentes();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<Componente> s) {
        formularioFragment.criaEAtualizaDinamicamenteComponentesNaTela(s, FormularioFragment.CRIA_COMPONENTE);
        super.onPostExecute(s);
    }
}
