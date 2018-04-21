package com.bruno.santander.santanderchallenge.investimento.presentation;

public class InvestimentoPresenter implements InvestimentoContract.Presenter {

    private InvestimentoContract.View view;

    public InvestimentoPresenter(InvestimentoContract.View view){
        this.view = view;
    }

    @Override
    public void getFund() {
//        TODO Obtain the data from JSON (fund.json)
    }

    @Override
    public void invest() {
//        TODO fake investing on it
    }
}
