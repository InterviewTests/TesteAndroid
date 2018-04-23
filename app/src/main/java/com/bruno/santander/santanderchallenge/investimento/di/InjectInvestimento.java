package com.bruno.santander.santanderchallenge.investimento.di;

import android.app.Activity;

import com.bruno.santander.santanderchallenge.investimento.presentation.InvestimentoContract;
import com.bruno.santander.santanderchallenge.investimento.presentation.InvestimentoPresenter;

public class InjectInvestimento {

    public static void inject(Activity activity, InvestimentoContract.View view){
        InvestimentoPresenter presenter = new InvestimentoPresenter(activity, view);

        view.setPresenter(presenter);
    }
}
