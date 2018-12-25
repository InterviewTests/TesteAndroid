package com.seletiva.santander.investment.ui.tabs;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.seletiva.santander.investment.ui.BasePresenter;
import com.seletiva.santander.investment.ui.tabs.adapters.ViewPagerAdapter;

public interface Tabs {
    interface View {
        void addViewPagerAdapter(ViewPagerAdapter adapter);
        void addViewPagerToLayout();
        void updateToolbarTitleTo(String title);

        String getContactTabTitle();
        String getInvestimentsTabTitle();
    }

    interface Presenter extends BasePresenter {

    }
}
