package com.meteoro.testeandroid.ui.main.presentation.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.meteoro.testeandroid.R;
import com.meteoro.testeandroid.ui.contact.ContactFragment;
import com.meteoro.testeandroid.ui.investiment.presentation.InvestimentFragment;

import java.util.ArrayList;
import java.util.List;

public class MainFragmentAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragmentList;
    private List<String> fragmentTitleList;

    public MainFragmentAdapter(FragmentManager fm, Context context) {
        super(fm);
        fragmentList = new ArrayList<>();
        fragmentList.add(InvestimentFragment.newInstance());
        fragmentList.add(ContactFragment.newInstance());

        fragmentTitleList = new ArrayList<>();
        fragmentTitleList.add(context.getString(R.string.tab_title_investiment));
        fragmentTitleList.add(context.getString(R.string.tab_title_contact));
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitleList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
