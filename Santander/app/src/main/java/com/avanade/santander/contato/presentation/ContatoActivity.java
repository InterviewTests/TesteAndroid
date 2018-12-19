package com.avanade.santander.contato.presentation;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.avanade.santander.Injection;
import com.avanade.santander.R;
import com.avanade.santander.fundos.presentation.FundosActivity;
import com.avanade.santander.util.ActivityUtils;
import com.avanade.santander.util.EspressoIdlingResource;

public class ContatoActivity extends FragmentActivity {

    ContatoPresenter mContatoPresenter;

    Button btnContato;
    Button btnInvestimento;
    View barraInvestimento;
    View barraContato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.contato_activity);
        setActionButtons();

        ContatoFragment contatoFragment =
                (ContatoFragment) getSupportFragmentManager().findFragmentById(R.id.contato_container);

        if (contatoFragment == null) {
            contatoFragment = ContatoFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), contatoFragment, R.id.contato_container);
        }

        // Create the presenter
        new ContatoPresenter(
                Injection.provideUseCaseHandler(),
                contatoFragment,
                Injection.provideGetCells(getApplicationContext())
        );
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @VisibleForTesting
    public IdlingResource getCountingIdlingResource() {
        return EspressoIdlingResource.getIdlingResource();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void setActionButtons() {
        btnContato = findViewById(R.id.contato_btn_contato);
        btnContato.setBackgroundColor(getResources().getColor(R.color.colorSantanderDark));
        btnContato.setOnClickListener((v) -> onResume());

        btnInvestimento = findViewById(R.id.contato_btn_investimento);
        btnInvestimento.setBackgroundColor(getResources().getColor(R.color.colorSantander));
        btnInvestimento.setOnClickListener((v) -> {
            finish();
        });

        barraInvestimento = findViewById(R.id.contato_barra_investimento);
        barraInvestimento.setVisibility(View.GONE);
        barraInvestimento.setBackgroundColor(getResources().getColor(R.color.colorSantanderDark));

        barraContato = findViewById(R.id.contato_barra_contato);
        barraContato.setVisibility(View.VISIBLE);
        barraContato.setBackgroundColor(getResources().getColor(R.color.colorSantanderDark));
    }


}
