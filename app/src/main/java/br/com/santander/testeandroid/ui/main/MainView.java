package br.com.santander.testeandroid.ui.main;

import java.util.List;

public interface MainView {
    void setupViewPager(TabsAdapter adapter);

    List<String> getTabsTitles();

    void setupTabs();
}
