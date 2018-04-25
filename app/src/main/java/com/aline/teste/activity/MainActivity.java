package com.aline.teste.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.aline.teste.MVP.presenter.PresenterContato;
import com.aline.teste.R;
import com.aline.teste.Utils;

public class MainActivity extends AppCompatActivity {

    Utils utils;
    ScrollView scrollView;
    PresenterContato presenterContato = new PresenterContato();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.btn_contato));*/

        utils = new Utils();
        scrollView = findViewById(R.id.scroll);

        try{
            boolean stateNetwork = utils.getStateNetworkInfo(this);
            if(stateNetwork){
                presenterContato.callNetworkContato();
            } else{
                Snackbar.make(scrollView, getString(R.string.snack_sem_conexao),
                        Snackbar.LENGTH_SHORT).show();
            }
        }catch(Exception e){
            Log.e("MAIN", "ERROR : " + e.getMessage());
        }

        TextView textView = findViewById(R.id.txt_enviar);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Investimento.class));
            }
        });






    }
}
