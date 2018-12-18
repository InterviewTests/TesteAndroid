package com.avanade.santander.fundos.presentation;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.avanade.santander.Injection;
import com.avanade.santander.R;
import com.avanade.santander.util.ActivityUtils;
import com.avanade.santander.util.EspressoIdlingResource;

/**
 * Camada de apresentação - View (Container)
 */
public class FundosActivity extends FragmentActivity {

    FundosPresenter fundosPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fundos_activity);

        FundosFragment fundosFragment = (FundosFragment) getSupportFragmentManager().findFragmentById(R.id.container);

        if (fundosFragment == null) {
            // Create the fragment
            fundosFragment = FundosFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fundosFragment, R.id.container);
        }

        // Create the presenter
        fundosPresenter = new FundosPresenter(
                Injection.provideUseCaseHandler(),
                fundosFragment,
                Injection.provideGetFundos()
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

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setMessage("Finalizar o App Santander?")
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.d("BACK PRESSED", "Alert: Finalizar Aplicativo? -> Clicou em SIM");
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                            finishAndRemoveTask();
                        else
                            finish();
                    }
                })
                .setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.d("BACK PRESSED", "Alert: Finalizar Aplicativo? -> Clicou em NÃO");
                        dialog.dismiss();
                    }
                })
        ;
        AlertDialog dialog = builder.create();
        dialog.show();
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









}

