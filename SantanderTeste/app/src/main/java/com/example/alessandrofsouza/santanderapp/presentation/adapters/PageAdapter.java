package com.example.alessandrofsouza.santanderapp.presentation.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class PageAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ArrayList<String> title = new ArrayList<>();

    public PageAdapter(FragmentManager fm) {
        super(fm);
    }

    public void add(Fragment frag, String title) {
        this.fragments.add(frag);
        this.title.add(title);
    }

    @Override
    public Fragment getItem(int i) {
        return this.fragments.get(i);
    }

    @Override
    public int getCount() {
        return this.fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return this.title.get(position);
    }
}
