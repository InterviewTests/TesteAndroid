package com.nataliafavero.santander.ui;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.nataliafavero.santander.ui.detailFund.DetailFundFragment;

/**
 * Created by nataliafavero on 11/09/18.R
 */

public class StartFragmentPageAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public StartFragmentPageAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {

        return DetailFundFragment.newInstance();
//        switch (position) {
//            case 0:
//                return DetailFundFragment.newInstance();
//            case 1:
//                //TODO: call Contact
//                return null;
//            default:
//                return DetailFundFragment.newInstance();
//        }
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public CharSequence getPageTitle(int position) {
//        return super.getPageTitle(position);
        return "Teste";
    }
}
