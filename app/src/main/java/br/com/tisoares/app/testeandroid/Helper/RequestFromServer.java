package br.com.tisoares.app.testeandroid.Helper;

import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;


import br.com.tisoares.app.testeandroid.R;

/**
 * Created by TIAGO SOARES on 12/12/2018.
 */
public abstract class RequestFromServer {


    private String TAG = "RequestsFromServer";
    private ProgressDialog pDialog;
    private Activity activity;

    public RequestFromServer(Activity pActivity, String url){
        this.activity = pActivity;

        // Progress dialog
        pDialog = new ProgressDialog(pActivity);
        pDialog.setCancelable(false);

        // chama o metodo de comunicação com o servidor
        sendToServer(url);
    }

    /**
     * Envia para o servidor a solicitação dos dados
     * @param url Endereço da end-point
     */
    private void sendToServer(String url){
        String tag_string_req = url;
        Log.d(TAG, "URL: " + tag_string_req);

        pDialog.setMessage(activity.getString(R.string.processando));
        pDialog.show();

        // Faz a chamada para o servidor
        StringRequest strReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                hideDialog();
                // Chama o metodo abstrato com a resposta do servidor
                onReceive(response);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error.networkResponse == null) {
                    onError(activity.getString(R.string.erro_msg_off));
                }else{
                    onError(error.getMessage()+ " CODE: "+error.networkResponse.statusCode);
                }
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hideDialog();
            }
        });

        //Define o timout e a politica de tentativa
        strReq.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

    }

    /**
     * Fecha do dialog de processando
     */
    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    /**
     * Retorna a resposta do servidor
     * @param response
     */
    public abstract void onReceive(String response);

    /**
     * Executa em caso de erro
     * @param erros
     */
    public abstract void onError(String erros);

}
