package com.meteoro.testeandroid.ui.investiment.presentation;

import com.meteoro.testeandroid.core.lifecycle.AutomaticUnsubscriber;

import javax.inject.Inject;

public class InvestimentPresenter implements InvestimentContract.Presenter {

    private AutomaticUnsubscriber automaticUnsubscriber;

    @Inject
    public InvestimentPresenter(AutomaticUnsubscriber automaticUnsubscriber) {
        this.automaticUnsubscriber = automaticUnsubscriber;
    }
}
