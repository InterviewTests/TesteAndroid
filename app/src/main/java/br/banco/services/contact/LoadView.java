package br.banco.services.contact;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import br.banco.services.R;
import br.banco.services.app.utils.ReactAplication;

public class LoadView  extends AppCompatActivity implements ILoad.Views {




    public ReactAplication RX = new ReactAplication();
    public final String TAG ="LOADR";
    public Context context;
    private ProgressBar progressBar;
    private int messageCode = 4; // Error

    private static ILoad.Presenter presenter;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        this.context = getApplicationContext();
        initProgressBar(); //01


        presenter = new LoadPresenter();
        ((LoadPresenter) presenter).setView(this); //02

        presenter.onLoadTask(savedInstanceState, context); //03




    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
       // outState.putParcelableArrayList(KEY_LOAD, presenter.getLoadFor());
        super.onSaveInstanceState(outState);
    }



    @Override
    public void onResume() {
        super.onResume();

        // checkOnResume();
    }


    public void onSuccessView(String areaName){

       // br.banco.services.fund.option.FundOptionView.class
        //br.banco.services.fund.detail.DetailView;

        hideProgressBar();

        startActivity(new Intent(this,
                br.banco.services.contact.ContactView.class));
        overridePendingTransition(R.anim.an_entrar_esquerda,
                R.anim.an_sair_esquerda);
        finish();
    }

    @Nullable
    public void onErrorView(int msgCode){

        Handler handler;
        messageCode = msgCode;
        handler = new Handler();
        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {

                hideProgressBar();

                Intent intent;
                intent = new Intent(getApplicationContext(),
                        br.banco.services.app.alert.AlertView.class );
                intent.putExtra("messageCode", messageCode);
                startActivity(intent);

                overridePendingTransition(R.anim.an_sair, R.anim.an_entrar);
                // finish();
            }
        }, 1000);

    } //onErrorView

    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    public void initProgressBar() {
        RX.onMessage(TAG, "V/initProgressBar", context );
        progressBar = findViewById(R.id.TemplateProgressbar);

    }





}