package br.com.santander.testeandroid.ui.main;

import java.util.List;

public interface MainView {
    void setupViewPager(TabsAdapter adapter);
    List<String> getTabsTitles();
    void setupTabs();
    void updateTabLayout(int index, boolean selected);
    void generateCustomTab(int index, String text);
}
