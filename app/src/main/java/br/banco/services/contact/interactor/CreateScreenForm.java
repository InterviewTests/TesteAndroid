package br.banco.services.contact.interactor;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import br.banco.services.R;
import br.banco.services.contact.data.local.FromFile;
import br.banco.services.contact.data.local.FromPreferences;


/**
 *  Gerencia oo layout form de contato
 *  1 - tenta caregar das preferencias
 *  2 - caso nao exista nas prefs, busca na internet
 *  3 - cria / apaga / retorna campos / verifica se foi criada
 *
 */


public class CreateScreenForm extends AppCompatActivity   { //implements ILoadTask

    private Context context;
    private FromPreferences fromPref;
    private FromFile fromFile;
    private TextView tvTest1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teste);

        context = getApplicationContext();
        tvTest1 = (TextView) findViewById(R.id.tvTest);


        /**
         *
         *  Carregar  + salvar = json
         *
         */





              //  fromFile = new FromFile(context);

        /**
         *
         *  Carregar = prefs
         *
         */

        //  fromPref = new FromPreferences(context);
           //loadPrefs();




    }






    private void loadPrefs(){

        String localDir ="";
        String fileName ="";

        if(fromPref.isSPStart()){ // && saveData

             String TESTE_READ =  fromPref.onRead(localDir,  fileName, context);

            // fromPref.setSPFullName("Joe Smith 555");
            // fromPref.setSPEmail("joe.smith@site.com 333");
            // fromPref.setSPPhone("1188886666 888");
            // fromPref.setSPEmailSave(0);


            tvTest1.setText(TESTE_READ);


        }else{




            tvTest1.setText("Erro prefs nao salvas! ");

        }



       // prefForm.deleteSP(context);

    }




}
