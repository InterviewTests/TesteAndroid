package rrzaniolo.testandroidsantander.main.adapters;

/*
 * Created by Rodrigo Rodrigues Zaniolo on 5/7/2018.
 * All rights reserved.
 */

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import rrzaniolo.testandroidsantander.main.contact.ContactView;
import rrzaniolo.testandroidsantander.main.investment.InvestmentView;

/**
 * A FragmentPagerAdapter implementation to handle the Tabs on the MainView.
 * */
public class MainViewPagerAdapter extends FragmentPagerAdapter{

    //region --- Variables
    private List<String> tabTitles;
    @NonNull private List<String> getTabTitles() {
        return tabTitles;
    }

    private void setTabTitles(@NonNull List<String> tabTitles) {
        this.tabTitles = tabTitles;
    }
    //endregion

    //region --- Constructor
    public MainViewPagerAdapter(@NonNull FragmentManager fm, @NonNull List<String> tabTitles) {
        super(fm);
        setTabTitles(tabTitles);
    }
    //endregion

    //endregion

    //region --- FragmentPagerAdapter Methods
    /**
     * This method determines which InnerView will be shown on each Tab.
     * */
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return InvestmentView.getInstance();
            case 1:
                return ContactView.getInstance();
            default:
                return new Fragment();
        }
    }

    /**
     * This method returns the number of Tabs.
     * */
    @Override
    public int getCount() {
        return 2;
    }
    //endregion

    /**
     * This determines the title for each tab
     * */
    @Override
    public CharSequence getPageTitle(int position) {
        return getTabTitles().get(position);
    }
}
