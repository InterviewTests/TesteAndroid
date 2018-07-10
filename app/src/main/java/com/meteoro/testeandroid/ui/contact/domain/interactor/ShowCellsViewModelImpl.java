package com.meteoro.testeandroid.ui.contact.domain.interactor;

import com.meteoro.testeandroid.core.di.qualifers.UiScheduler;
import com.meteoro.testeandroid.ui.contact.domain.model.CellsViewModel;
import com.meteoro.testeandroid.ui.contact.presentation.ContactContract;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;

public class ShowCellsViewModelImpl implements ShowCellsViewModel {

    private Scheduler uiScheduler;
    private ContactContract.View view;

    @Inject
    public ShowCellsViewModelImpl(@UiScheduler Scheduler uiScheduler,
                                  ContactContract.View view) {
        this.uiScheduler = uiScheduler;
        this.view = view;
    }

    @Override
    public Observable<CellsViewModel> call(Observable<CellsViewModel> observable) {
        return observable
                .observeOn(uiScheduler)
                .subscribeOn(uiScheduler)
                .doOnNext(this::showViewModel)
                .doOnError(this::showError)
                .onErrorResumeNext(Observable.empty());
    }

    private void showViewModel(CellsViewModel viewModel) {
        view.showViewModel(viewModel);
    }

    private void showError(Throwable throwable) {
        throwable.printStackTrace();
        view.showError();
    }
}
