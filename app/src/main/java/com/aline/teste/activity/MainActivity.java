package com.aline.teste.activity;

import android.net.ConnectivityManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ScrollView;

import com.aline.teste.MVP.MVP;
import com.aline.teste.MVP.presenter.PresenterContato;
import com.aline.teste.R;
import com.aline.teste.Utils;

import okhttp3.internal.Util;

public class MainActivity extends AppCompatActivity {

    Utils utils;
    ScrollView scrollView;
    PresenterContato presenterContato = new PresenterContato();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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






    }
}
