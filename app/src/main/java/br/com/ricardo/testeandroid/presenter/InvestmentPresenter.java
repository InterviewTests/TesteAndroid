package br.com.ricardo.testeandroid.presenter;

import br.com.ricardo.testeandroid.view.InvestmentView;

public interface InvestmentPresenter {

    void attachView(InvestmentView view);
    void detachView();
    void requestInvestmentInfos();

}
