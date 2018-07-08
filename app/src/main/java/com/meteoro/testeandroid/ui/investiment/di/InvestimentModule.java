package com.meteoro.testeandroid.ui.investiment.di;

import android.arch.lifecycle.LifecycleOwner;

import com.meteoro.testeandroid.core.di.PerFragment;
import com.meteoro.testeandroid.core.lifecycle.AutomaticUnsubscriber;
import com.meteoro.testeandroid.core.lifecycle.LifecycleUnsubscriber;
import com.meteoro.testeandroid.ui.investiment.domain.interactor.GetFund;
import com.meteoro.testeandroid.ui.investiment.domain.interactor.GetFundImpl;
import com.meteoro.testeandroid.ui.investiment.domain.interactor.ShowLoadingInvestiment;
import com.meteoro.testeandroid.ui.investiment.domain.interactor.ShowLoadingInvestimentImpl;
import com.meteoro.testeandroid.ui.investiment.presentation.InvestimentContract;
import com.meteoro.testeandroid.ui.investiment.presentation.InvestimentFragment;
import com.meteoro.testeandroid.ui.investiment.presentation.InvestimentPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class InvestimentModule {

    private InvestimentFragment fragment;

    public InvestimentModule(InvestimentFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @PerFragment
    InvestimentContract.View view() {
        return fragment;
    }

    @Provides
    @PerFragment
    InvestimentContract.Presenter presenter(InvestimentPresenter presenter) {
        return presenter;
    }

    @Provides
    @PerFragment
    LifecycleOwner lifecycleOwner() {
        return fragment;
    }

    @Provides
    @PerFragment
    AutomaticUnsubscriber automaticUnsubscriber(LifecycleUnsubscriber impl) {
        return impl;
    }

    @Provides
    @PerFragment
    ShowLoadingInvestiment showLoadingInvestiment(ShowLoadingInvestimentImpl impl) {
        return impl;
    }

    @Provides
    @PerFragment
    GetFund getFund(GetFundImpl impl) {
        return impl;
    }
}
