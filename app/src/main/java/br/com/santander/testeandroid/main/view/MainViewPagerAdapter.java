package br.com.santander.testeandroid.main.view;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class MainViewPagerAdapter extends FragmentPagerAdapter {
    private List<String> tabTitles;

    public MainViewPagerAdapter(@NonNull FragmentManager fm, @NonNull List<String> tabTitles) {
        super(fm);
        setTabTitles(tabTitles);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                //call first screen
            case 1:
                //call second screen
            default:
                return new Fragment();
        }
    }

    @Override
    public int getCount() {
        return tabTitles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return getTabTitles().get(position);
    }

    @NonNull
    private List<String> getTabTitles() {
        return tabTitles;
    }

    private void setTabTitles(@NonNull List<String> tabTitles) {
        this.tabTitles = tabTitles;
    }

}
