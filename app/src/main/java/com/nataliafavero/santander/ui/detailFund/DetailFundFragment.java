package com.nataliafavero.santander.ui.detailFund;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nataliafavero.santander.R;

/**
 * Created by nataliafavero on 11/09/18.
 */

public class DetailFundFragment extends Fragment implements DetailFundContract.View {

    public static DetailFundFragment newInstance() {
        return new DetailFundFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail_fund, container, false);
    }

    @Override
    public void setPresenter(DetailFundContract.Presenter presenter) {

    }

    @Override
    public void showFund() {

    }
}
