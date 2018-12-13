package br.com.tisoares.app.testeandroid.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import br.com.tisoares.app.testeandroid.Config.AppConfig;
import br.com.tisoares.app.testeandroid.Helper.RequestFromServer;
import br.com.tisoares.app.testeandroid.Model.Field;
import br.com.tisoares.app.testeandroid.R;

public class FormActivity extends AppCompatActivity {

    private ArrayList<Field> fieldList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        this.fieldList = new ArrayList<Field>();

        solicitaFields();
    }

    private void solicitaFields() {
        new RequestFromServer(this, AppConfig.SVR_CELLS_FORM) {
            @Override
            public void onReceive(String response) {
                recebeCampos(response);
            }

            @Override
            public void onError(String erros) {
                Toast.makeText(FormActivity.this, erros, Toast.LENGTH_LONG).show();
            }
        };
    }

    /*
        Recebe o JSONArray do servidor e cria o List de Field;
    */
    private void recebeCampos(String response) {
        try {
            JSONObject j = new JSONObject(response);
            JSONArray jsonArray = j.getJSONArray("cells");

            for (int i = 0; i < jsonArray.length(); i++){
                fieldList.add(new Field(jsonArray.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        StringBuilder b = new StringBuilder();
        for (Field f: fieldList) {
            b.append("FIELDS: "+f.getMessage());
        }
        Log.d("LOG: ", b.toString());
    }


}
