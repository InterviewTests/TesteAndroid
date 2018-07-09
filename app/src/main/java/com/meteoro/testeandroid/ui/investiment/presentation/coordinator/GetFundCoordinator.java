package com.meteoro.testeandroid.ui.investiment.presentation.coordinator;

import com.meteoro.testeandroid.ui.investiment.domain.interactor.ConvertScreenToViewModel;
import com.meteoro.testeandroid.ui.investiment.domain.interactor.GetFund;
import com.meteoro.testeandroid.ui.investiment.domain.interactor.ShowLoadingInvestiment;
import com.meteoro.testeandroid.ui.investiment.domain.model.ScreenViewModel;

import javax.inject.Inject;

import rx.Observable;

public class GetFundCoordinator implements Observable.Transformer<String, ScreenViewModel> {

    private ShowLoadingInvestiment showLoadingInvestiment;
    private GetFund getFund;
    private ConvertScreenToViewModel convertScreenToViewModel;

    @Inject
    public GetFundCoordinator(ShowLoadingInvestiment showLoadingInvestiment,
                              GetFund getFund,
                              ConvertScreenToViewModel convertScreenToViewModel) {
        this.showLoadingInvestiment = showLoadingInvestiment;
        this.getFund = getFund;
        this.convertScreenToViewModel = convertScreenToViewModel;
    }

    @Override
    public Observable<ScreenViewModel> call(Observable<String> observable) {
        return observable
                .compose(showLoadingInvestiment)
                .compose(getFund)
                .compose(convertScreenToViewModel);
    }
}
