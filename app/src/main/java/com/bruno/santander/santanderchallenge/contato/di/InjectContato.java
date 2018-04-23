package com.bruno.santander.santanderchallenge.contato.di;

import android.app.Activity;

import com.bruno.santander.santanderchallenge.contato.presentation.ContatoContract;
import com.bruno.santander.santanderchallenge.contato.presentation.ContatoPresenter;

public class InjectContato {

    public static void inject(Activity activity, ContatoContract.View view){
        ContatoPresenter presenter = new ContatoPresenter(activity, view);

        view.setPresenter(presenter);
    }

}
