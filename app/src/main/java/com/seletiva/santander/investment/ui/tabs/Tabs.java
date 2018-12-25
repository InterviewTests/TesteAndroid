package com.seletiva.santander.investment.ui.tabs;

import com.seletiva.santander.investment.ui.BasePresenter;
import com.seletiva.santander.investment.ui.tabs.adapters.ViewPagerAdapter;

public interface Tabs {
    interface View {
        void addViewPagerAdapter(ViewPagerAdapter adapter);
        void addViewPagerToLayout();

        String getContactTabTitle();
        String getInvestimentsTabTitle();
    }

    interface Presenter extends BasePresenter {

    }
}
