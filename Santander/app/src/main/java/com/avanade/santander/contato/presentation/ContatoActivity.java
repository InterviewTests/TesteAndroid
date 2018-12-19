package com.avanade.santander.contato.presentation;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.avanade.santander.Injection;
import com.avanade.santander.R;
import com.avanade.santander.util.ActivityUtils;

public class ContatoActivity extends FragmentActivity {

    ContatoPresenter mContatoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.common_layout_activity);

        ContatoFragment contatoFragment =
                (ContatoFragment) getSupportFragmentManager().findFragmentById(R.id.container);

        if (contatoFragment == null) {
            contatoFragment = ContatoFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), contatoFragment, R.id.container);
        }

        // Create the presenter
        new ContatoPresenter(
                Injection.provideUseCaseHandler(),
                contatoFragment,
                Injection.provideGetCells(getApplicationContext())
        );
    }


}
