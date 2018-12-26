package com.seletiva.santander.investment.tabs;

import android.support.v4.app.FragmentManager;

import com.seletiva.santander.investment.ui.tabs.Tabs;
import com.seletiva.santander.investment.ui.tabs.TabsPresenter;
import com.seletiva.santander.investment.ui.tabs.adapters.ViewPagerAdapter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;

public class TestTabsPresenter {
    @Mock private Tabs.View view;
    @Mock private FragmentManager fragmentManager;

    private TabsPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new TabsPresenter(view, fragmentManager);
    }

    @Test
    public void test_setupViewPageAdapter() {
        presenter.start();

        InOrder inOrder = Mockito.inOrder(view);
        inOrder.verify(view, times(1)).getInvestmentsTabTitle();
        inOrder.verify(view, times(1)).getContactTabTitle();
        inOrder.verify(view, times(1)).addViewPagerToLayout();
    }
}
