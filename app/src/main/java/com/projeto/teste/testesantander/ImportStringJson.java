package com.projeto.teste.testesantander;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImportStringJson extends AsyncTask<String, String, String> {

    StringBuilder result = new StringBuilder();

    private ProgressDialog pDialog;
    private Context ctx;
    HttpURLConnection cc;

    public ImportStringJson(Context ctx)
    {
        this.ctx=ctx;
        this.pDialog = new ProgressDialog(ctx);
    }

    public AsyncResponse delegate = null;

    @Override
    protected String doInBackground(String... args) {

        ///StringBuilder result = new StringBuilder();

        try {
            URL url = new URL("https://floating-mountain-50292.herokuapp.com/fund.json");
            cc = (HttpURLConnection) url.openConnection();
            //set timeout for reading InputStream
            cc.setReadTimeout(5000);
            // set timeout for connection
            cc.setConnectTimeout(5000);
            //set HTTP method to GET
            cc.setRequestMethod("GET");
            //set it to true as we are connecting for input
            cc.setDoInput(true);
            //reading HTTP response code
            int response = cc.getResponseCode();
            //if response code is 200 / OK then read Inputstream
            if (response == HttpURLConnection.HTTP_OK) {
                InputStream in = cc.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                //delegate.processFinish(result.toString());
                in.close();
                reader.close();
              }
        }catch( Exception e) {
            e.printStackTrace();
            //delegate.processFinish("");
        }
        finally {
            if (cc != null) {
                cc.disconnect();
            }

        }
        return null;
    }

    //Retorna objeto investimento as informações retornadas do JSON
    public Investimento getInvestimento(String jsonString) {
        Investimento invest = new Investimento();
        try {
            JSONObject investimentoJason  = new JSONObject(jsonString);
            invest.setTitulo(investimentoJason.getString("titulo"));
            invest.setTitulo(investimentoJason.getString("nomeFundo"));
            invest.setTitulo(investimentoJason.getString("oQueE"));
            invest.setTitulo(investimentoJason.getString("definicao"));
            invest.setTitulo(investimentoJason.getString("tituloRisco"));
            invest.setTitulo(investimentoJason.getString("risco"));
            invest.setTitulo(investimentoJason.getString("tituloInfo"));

        } catch (JSONException e) {
            Log.e("Erro", "Erro no parsing do JSON", e);
        }
        return invest;
    }



    //Converte objeto InputStream para String
    private String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = ProgressDialog.show(ctx, "", "Carregando...", true,false);
    }

    @Override
    protected void onPostExecute(String resultJ) {
        if (pDialog.isShowing()) {
            pDialog.dismiss();
        }
        delegate.processFinish(result.toString());
    }

}

