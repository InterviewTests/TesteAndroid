package com.meteoro.testeandroid.ui.investiment.presentation.coordinator;

import com.meteoro.testeandroid.core.data.model.Screen;
import com.meteoro.testeandroid.ui.investiment.domain.interactor.GetFund;
import com.meteoro.testeandroid.ui.investiment.domain.interactor.ShowLoadingInvestiment;

import javax.inject.Inject;

import rx.Observable;

public class GetFundCoordinator implements Observable.Transformer<String, Screen> {

    private ShowLoadingInvestiment showLoadingInvestiment;
    private GetFund getFund;

    @Inject
    public GetFundCoordinator(ShowLoadingInvestiment showLoadingInvestiment,
                              GetFund getFund) {
        this.showLoadingInvestiment = showLoadingInvestiment;
        this.getFund = getFund;
    }

    @Override
    public Observable<Screen> call(Observable<String> observable) {
        return observable
                .compose(showLoadingInvestiment)
                .compose(getFund);
    }
}
