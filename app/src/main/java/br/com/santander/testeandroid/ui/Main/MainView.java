package br.com.santander.testeandroid.ui.Main;

import java.util.List;

public interface MainView {
    void setupViewPager(TabsAdapter adapter);

    List<String> getTabsTitles();

    void setupTabs();
}
