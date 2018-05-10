package com.carpinelli.testeandroid.ui.invest;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.carpinelli.testeandroid.R;
import com.carpinelli.testeandroid.model.Screen;

public class InvestFragment extends Fragment implements MvpInvest.View {

    private static final String TAG = InvestFragment.class.getSimpleName();

    private InvestPresenter investPresenter;

    public InvestFragment() {
        investPresenter = new InvestPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_invest, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (investPresenter != null) {
            investPresenter.onStart();
        }

    }

    @Override
    public void onScreenReady(Screen screen) {
        Log.d(TAG, "onScreenReady: ");
    }
}
