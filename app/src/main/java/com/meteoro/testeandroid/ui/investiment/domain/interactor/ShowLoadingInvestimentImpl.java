package com.meteoro.testeandroid.ui.investiment.domain.interactor;

import com.meteoro.testeandroid.core.di.qualifers.UiScheduler;
import com.meteoro.testeandroid.ui.investiment.presentation.InvestimentContract;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;

public class ShowLoadingInvestimentImpl implements ShowLoadingInvestiment {

    private Scheduler uiScheduler;
    private InvestimentContract.View view;

    @Inject
    public ShowLoadingInvestimentImpl(@UiScheduler Scheduler uiScheduler,
                                      InvestimentContract.View view) {
        this.uiScheduler = uiScheduler;
        this.view = view;
    }

    @Override
    public Observable<String> call(Observable<String> observable) {
        return observable
                .observeOn(uiScheduler)
                .subscribeOn(uiScheduler)
                .doOnNext(this::showLoading);
    }

    private void showLoading(String ignore) {
        view.showLoading();
    }
}
