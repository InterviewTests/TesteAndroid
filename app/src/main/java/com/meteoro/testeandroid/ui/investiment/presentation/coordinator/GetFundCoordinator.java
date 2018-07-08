package com.meteoro.testeandroid.ui.investiment.presentation.coordinator;

import com.meteoro.testeandroid.core.data.model.Screen;
import com.meteoro.testeandroid.ui.investiment.domain.interactor.GetFund;

import javax.inject.Inject;

import rx.Observable;

public class GetFundCoordinator implements Observable.Transformer<String, Screen> {

    private GetFund getFund;

    @Inject
    public GetFundCoordinator(GetFund getFund) {
        this.getFund = getFund;
    }

    @Override
    public Observable<Screen> call(Observable<String> observable) {
        return observable
                .compose(getFund);
    }
}
