package com.seletiva.santander.investment.ui.tabs;

import android.support.v4.app.FragmentManager;

import com.seletiva.santander.investment.ui.form.MainFormFragment_;
import com.seletiva.santander.investment.ui.investments.InvestmentsFragment_;
import com.seletiva.santander.investment.ui.tabs.adapters.ViewPagerAdapter;

public class TabsPresenter implements Tabs.Presenter {
    private final Tabs.View view;
    private final FragmentManager fragmentManager;

    public TabsPresenter(Tabs.View view, FragmentManager fragmentManager) {
        this.view = view;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public void start() {
        configureTabAdapter();
    }

    private void configureTabAdapter() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(fragmentManager);
        adapter.addFragment(new InvestmentsFragment_(), view.getInvestmentsTabTitle());
        adapter.addFragment(new MainFormFragment_(), view.getContactTabTitle());

        view.addViewPagerAdapter(adapter);
        view.addViewPagerToLayout();
    }
}
