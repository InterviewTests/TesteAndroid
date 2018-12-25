package com.seletiva.santander.investment.ui.investments;

import android.support.v4.app.Fragment;

import com.seletiva.santander.investment.R;
import com.seletiva.santander.investment.ui.tabs.domain.TabClickEvent;

import org.androidannotations.annotations.EFragment;
import org.greenrobot.eventbus.EventBus;

@EFragment(R.layout.fragment_investments)
public class FragmentInvestments extends Fragment {
    public FragmentInvestments() {}

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getContext() != null) {
            if (isVisibleToUser) {
                EventBus.getDefault().post(new TabClickEvent(getString(R.string.tab_investments_title)));
            }
        }
    }
}
