package com.bruno.santander.santanderchallenge.investimento.presentation;

import com.bruno.santander.santanderchallenge.presentation.BaseView;

public interface InvestimentoContract {

    interface View extends BaseView<InvestimentoPresenter>{
        void onSuccessGetFund();
        void onSuccessInvestment();
    }

    interface Presenter{
        void getFund();
        void invest();
    }

}
