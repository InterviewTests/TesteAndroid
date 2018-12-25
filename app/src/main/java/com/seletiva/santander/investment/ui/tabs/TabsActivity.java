package com.seletiva.santander.investment.ui.tabs;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.seletiva.santander.investment.R;
import com.seletiva.santander.investment.ui.BaseActivity;
import com.seletiva.santander.investment.ui.tabs.adapters.ViewPagerAdapter;
import com.seletiva.santander.investment.ui.tabs.domain.TabClickEvent;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

@EActivity(R.layout.activity_tabs)
public class TabsActivity extends BaseActivity implements Tabs.View {
    @ViewById Toolbar toolbar;
    @ViewById TabLayout tablayout;
    @ViewById ViewPager viewPager;

    private TabsPresenter presenter;

    @AfterViews
    public void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        presenter = new TabsPresenter(this, getSupportFragmentManager());
        presenter.start();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onNewTitleEvent(Object event) {
        if (event.getClass() == TabClickEvent.class) {
            final String title = ((TabClickEvent) event).getTitle();
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public void addViewPagerAdapter(ViewPagerAdapter adapter) {
        viewPager.setAdapter(adapter);
    }

    @Override
    public void addViewPagerToLayout() {
        tablayout.setupWithViewPager(viewPager);
    }

    @Override
    public void updateToolbarTitleTo(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public String getContactTabTitle() {
        return getString(R.string.tab_contact_title);
    }

    @Override
    public String getInvestimentsTabTitle() {
        return getString(R.string.tab_investments_title);
    }
}
