package com.bruno.santander.santanderchallenge.investimento.presentation;

import com.bruno.santander.santanderchallenge.investimento.model.ScreenMapper;
import com.bruno.santander.santanderchallenge.presentation.BasePresenter;
import com.bruno.santander.santanderchallenge.presentation.BaseView;

public interface InvestimentoContract {

    interface View extends BaseView<InvestimentoPresenter>{
        void onSuccessGetFund(ScreenMapper fund);
        void onSuccessInvestment();
    }

    interface Presenter extends BasePresenter{
        void getFund();
        void invest();
    }

}
