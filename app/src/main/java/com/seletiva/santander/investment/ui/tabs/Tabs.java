package com.seletiva.santander.investment.ui.tabs;

import com.seletiva.santander.investment.ui.BasePresenter;
import com.seletiva.santander.investment.ui.tabs.adapters.ViewPagerAdapter;

/**
 * Contrato View/Presenter da activity de Tabs
 */
public interface Tabs {
    interface View {
        /**
         * Adiciona adapter ao ViewPager
         * @param adapter adapter a ser adicionado ao ViewPager
         */
        void addViewPagerAdapter(ViewPagerAdapter adapter);

        /**
         * Adiciona ViewPager ao Layout
         */
        void addViewPagerToLayout();

        /**
         * Recupera string ser utilizada como título da tabbar (Contatos)
         * @return string ser utilizada como título da tabbar
         */
        String getContactTabTitle();

        /**
         * Recupera string ser utilizada como título da tabbar (Investimentos)
         * @return string ser utilizada como título da tabbar
         */
        String getInvestmentsTabTitle();
    }

    interface Presenter extends BasePresenter {}
}
