package com.meteoro.testeandroid.ui.investiment.presentation;

import android.util.Log;

import com.meteoro.testeandroid.core.lifecycle.AutomaticUnsubscriber;
import com.meteoro.testeandroid.ui.investiment.presentation.coordinator.GetFundCoordinator;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;

public class InvestimentPresenter implements InvestimentContract.Presenter {

    private GetFundCoordinator getFundCoordinator;
    private AutomaticUnsubscriber automaticUnsubscriber;

    @Inject
    public InvestimentPresenter(GetFundCoordinator getFundCoordinator,
                                AutomaticUnsubscriber automaticUnsubscriber) {
        this.getFundCoordinator = getFundCoordinator;
        this.automaticUnsubscriber = automaticUnsubscriber;
    }

    @Override
    public void initializeContents() {
        Subscription subscription =
                Observable.just("")
                        .compose(getFundCoordinator)
                        .subscribe(screen -> {
                            Log.d("Presenter", "Screen " + screen.title());
                        }, Throwable::printStackTrace);
        automaticUnsubscriber.add(subscription);
    }
}
