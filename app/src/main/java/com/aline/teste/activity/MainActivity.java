package com.aline.teste.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.aline.teste.MVP.presenter.PresenterContato;
import com.aline.teste.R;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout layout;
    PresenterContato presenterContato = new PresenterContato();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.constraint_main);
        boolean checkRede = getCheckSate();

        if(checkRede){
            presenterContato.callNetworkContato();
        }else{
            Snackbar.make(layout, getString(R.string.snack_sem_conexao),
                    Snackbar.LENGTH_LONG).show();
        }

        TextView textView = findViewById(R.id.txt_enviar);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Investimento.class));
            }
        });
    }

    public boolean getCheckSate(){
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo inforRede = null;
        if(connectivityManager != null){
            inforRede = connectivityManager.getActiveNetworkInfo();
        } if(inforRede != null && inforRede.isConnected()){
            return  true;
        } else{
            return  false;
        }
    }

}

