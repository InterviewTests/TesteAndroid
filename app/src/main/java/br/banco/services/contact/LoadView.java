package br.banco.services.contact;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import br.banco.services.R;
import br.banco.services.app.utils.ReactAplication;
import br.banco.services.contact.data.IContact;
import br.banco.services.fund.detail.DetailModel;
import br.banco.services.fund.detail.DetailsPresenter;
import br.banco.services.fund.detail.IDetail;
import br.banco.services.fund.domain.national.ScreenFund;

public class LoadView  extends AppCompatActivity implements ILoad.Views {

    public final String TAG ="LOADR";
    public Context context;
    private ProgressBar progressBar;
    public ReactAplication RX = new ReactAplication();
    private int messageCode = 4; // Error

    private static ILoad.Presenter presenter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        initProgressBar();

        presenter = new LoadPresenter();
       ((LoadPresenter) presenter).setView(this);

        presenter.onLoadData(savedInstanceState, context);



    }



    /**
     * carrega conteudo ou mostra tela de erro
     *
     */


    public void onSuccessLoad(Context context, String local){


    }

    public void onErrorLoad(Context context, int msgCode){

        updateAlertView(msgCode, context);


    }


    /**
     *
     *  views
     *
     */


    public void updateAlertView( int msgCode, Context context){

        Handler handler;
        messageCode = msgCode;
        // delay
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent;
                intent = new Intent(getApplicationContext(),
                        br.banco.services.app.alert.AlertView.class );
                intent.putExtra("messageCode", messageCode);
                startActivity(intent);

                overridePendingTransition(R.anim.an_sair, R.anim.an_entrar);
                // finish();

            }
        }, 100);

        Log.d(TAG," / updateAlertView -> ERROR -> messageCode  = " +  messageCode);

    }

    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    public void initProgressBar() {
        progressBar = findViewById(R.id.TemplateProgressbar);

    }

}