package br.com.santander.testeandroid.investment;

import java.util.List;

import br.com.santander.testeandroid.investment.model.BaseInfo;
import br.com.santander.testeandroid.investment.model.MoreInfo;

public interface InvestmentContract {

    void showLoading();

    void hideLoading();

    void setListInfo(List<BaseInfo> infoList);

    void setHeaderInfo(String title, String fundName, String whatIs, String definition);

    void setMoreInfo(String title, MoreInfo moreInfo);

    void setRiskInfo(String title, Integer risk);

    void showContainer();

    void hideContainer();

    void setInvestmentListener();

    void showError();

    void hideError();

}
