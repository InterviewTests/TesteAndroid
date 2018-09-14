package com.nataliafavero.santander.ui;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.nataliafavero.santander.R;
import com.nataliafavero.santander.ui.createContact.CreateContactFragment;
import com.nataliafavero.santander.ui.createContact.CreateContactPresenter;
import com.nataliafavero.santander.ui.detailFund.DetailFundFragment;
import com.nataliafavero.santander.ui.detailFund.DetailFundPresenter;

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

        switch (position) {
            case 0:
                DetailFundFragment fragment = DetailFundFragment.newInstance();
                new DetailFundPresenter(fragment);
                return fragment;
            case 1:
                CreateContactFragment fragment1 = CreateContactFragment.newInstance();
                new CreateContactPresenter(fragment1);
                return fragment1;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getResources().getString(R.string.fund_title);
            case 1:
                return mContext.getResources().getString(R.string.contact_title);
            default:
                return mContext.getResources().getString(R.string.fund_title);
        }
    }
}
