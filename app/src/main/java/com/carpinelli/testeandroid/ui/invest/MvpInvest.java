package com.carpinelli.testeandroid.ui.invest;

import com.carpinelli.testeandroid.model.invest.Investment;
import com.carpinelli.testeandroid.di.Mvp;

public interface MvpInvest {

    interface View extends Mvp.View {

        void onInvestmentReady(Investment investment);
    }

    interface Presenter extends Mvp.Presenter {
        
        void onStart();
    }

}
