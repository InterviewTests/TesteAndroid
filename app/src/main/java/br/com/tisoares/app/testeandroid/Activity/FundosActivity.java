package br.com.tisoares.app.testeandroid.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.tisoares.app.testeandroid.Config.AppConfig;
import br.com.tisoares.app.testeandroid.Helper.RequestFromServer;
import br.com.tisoares.app.testeandroid.Model.Fundo;
import br.com.tisoares.app.testeandroid.R;

public class FundosActivity extends AppCompatActivity {

    private Fundo fundo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fundos);

        solicitaFundos();

    }

    private void solicitaFundos() {
        new RequestFromServer(this, AppConfig.SVR_FUNDOS) {
            @Override
            public void onReceive(String response) {
                recebeFundos(response);
            }

            @Override
            public void onError(String erros) {
                Toast.makeText(FundosActivity.this, erros, Toast.LENGTH_LONG).show();
            }
        };
    }

    private void recebeFundos(String response) {
       fundo = new Fundo();
       if (fundo.createByJSON(response)){
            //
       }else{
           Toast.makeText(FundosActivity.this, R.string.erro_carregar_fundo, Toast.LENGTH_LONG).show();
       }
    }
}
