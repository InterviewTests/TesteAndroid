package com.meteoro.testeandroid.ui.contact.domain.interactor;

import com.meteoro.testeandroid.core.di.qualifers.UiScheduler;
import com.meteoro.testeandroid.ui.contact.presentation.ContactContract;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;

public class ShowLoadingContactImpl implements ShowLoadingContact {

    private Scheduler uiScheduler;
    private ContactContract.View view;

    @Inject
    public ShowLoadingContactImpl(@UiScheduler Scheduler uiScheduler,
                                  ContactContract.View view) {
        this.uiScheduler = uiScheduler;
        this.view = view;
    }

    @Override
    public Observable<String> call(Observable<String> observable) {
        return observable
                .observeOn(uiScheduler)
                .subscribeOn(uiScheduler)
                .doOnNext(this::showLoading);
    }

    private void showLoading(String ignore) {
        view.showLoading();
    }
}
