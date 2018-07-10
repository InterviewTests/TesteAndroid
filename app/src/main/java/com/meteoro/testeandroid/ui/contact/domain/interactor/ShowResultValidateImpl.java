package com.meteoro.testeandroid.ui.contact.domain.interactor;

import com.meteoro.testeandroid.core.di.qualifers.UiScheduler;
import com.meteoro.testeandroid.ui.contact.domain.model.CellsViewModel;
import com.meteoro.testeandroid.ui.contact.presentation.ContactContract;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;

public class ShowResultValidateImpl implements ShowResultValidate {

    private Scheduler uiScheduler;
    private ContactContract.View view;

    @Inject
    public ShowResultValidateImpl(@UiScheduler Scheduler uiScheduler,
                                  ContactContract.View view) {
        this.uiScheduler = uiScheduler;
        this.view = view;
    }

    @Override
    public Observable<CellsViewModel> call(Observable<CellsViewModel> observable) {
        return observable
                .observeOn(uiScheduler)
                .subscribeOn(uiScheduler)
                .doOnNext(this::showViewModel);
    }

    private void showViewModel(CellsViewModel viewModel) {
        view.showViewModel(viewModel);
    }
}
