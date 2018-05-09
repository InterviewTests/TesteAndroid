package rrzaniolo.testandroidsantander.main.investment;

/*
 * Created by Rodrigo Rodrigues Zaniolo on 5/9/2018.
 * All rights reserved.
 */

import java.util.List;

import rrzaniolo.testandroidsantander.main.investment.adapter.InfoAdapter;
import rrzaniolo.testandroidsantander.network.investment.models.BaseInfo;
import rrzaniolo.testandroidsantander.network.investment.models.MoreInfo;

/**
 * The contract for InvestmentView and InvestmentPresenter.
 * */
public interface InvestmentContract {

    interface View{

        void setHeaderInfo(String title, String fundName, String subtitle, String definition);
        void setRiskInfo(String riskTitle, Integer risk);
        void setMoreInfo(String infoTitle, MoreInfo moreInfo);
        void setListInfo(List<BaseInfo> infoList);
        void setDownInfo(List<BaseInfo> downInfoList);
        void setInvestmentAction();

        void showLoading();
        void hideLoading();

        void showError();
        void hideError();
    }

    interface Presenter{

        void onStart();

        android.view.View.OnClickListener onInvestment();

        InfoAdapter getInfoAdapter(List<BaseInfo> infoList);
        InfoAdapter getDownInfoAdapter(List<BaseInfo> downInfoList);
    }
}
