package com.adenilson.testeandroid.base;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.adenilson.testeandroid.R;
import com.adenilson.testeandroid.contact.ui.ContactFragment;
import com.adenilson.testeandroid.investiment.ui.InvestmentFragment;

/**
 * @author: Adenilson N. da Silva Junior
 * On date: 09/06/2018
 */

public class PagerAdapter extends FragmentPagerAdapter {

    private static final int TAB_INVESTMENT = 0;
    private static final int TAB_CONTACT = 1;

    private static final int TAB_COUNT = 2;

    private Context mContext;

    public PagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case TAB_INVESTMENT:
                return InvestmentFragment.newInstance();
            case TAB_CONTACT:
                return ContactFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case TAB_INVESTMENT:
                return mContext.getString(R.string.title_investment);
            case TAB_CONTACT:
                return mContext.getString(R.string.title_contact);
        }
        return super.getPageTitle(position);
    }
}
