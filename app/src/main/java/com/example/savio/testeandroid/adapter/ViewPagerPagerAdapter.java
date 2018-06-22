package com.example.savio.testeandroid.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerPagerAdapter extends android.support.v4.app.FragmentPagerAdapter{

    private final List<Fragment> fragment_list = new ArrayList<>();
    private final List<String> tabs_titles = new ArrayList<>();

    public ViewPagerPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        return fragment_list.get(position);
    }

    @Override
    public int getCount() {

        return tabs_titles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs_titles.get(position);
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    //add new fragment to create a tab
    public void addFragment(Fragment fragment, String title){

        fragment_list.add(fragment);
        tabs_titles.add(title);

    }

}
