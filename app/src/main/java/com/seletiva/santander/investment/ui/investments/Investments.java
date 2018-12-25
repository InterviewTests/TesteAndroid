package com.seletiva.santander.investment.ui.investments;

import com.seletiva.santander.investment.ui.BasePresenter;
import com.seletiva.santander.investment.ui.BaseView;
import com.seletiva.santander.investment.ui.investments.adapters.InvestmentsAdapter;
import com.seletiva.santander.investment.ui.investments.domain.MoreInfo;

public interface Investments {
    interface View extends BaseView {
        void configureHeader(String title, String fundName, String whatIs, String definition);

        void configureRiskBar(String riskTitle, int risk);

        void configureInfo(String infoTitle, MoreInfo moreInfo);

        void configureInvestmentsList(InvestmentsAdapter adapter);
    }

    interface Presenter extends BasePresenter {
        void loadFunds();
    }
}
