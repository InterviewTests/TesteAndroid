package com.meteoro.testeandroid.ui.investiment.domain.interactor;

import com.meteoro.testeandroid.core.di.qualifers.UiScheduler;
import com.meteoro.testeandroid.ui.investiment.domain.model.ScreenViewModel;
import com.meteoro.testeandroid.ui.investiment.presentation.InvestimentContract;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;

public class ShowScreenViewModelImpl implements ShowScreenViewModel {

    private Scheduler uiScheduler;
    private InvestimentContract.View view;

    @Inject
    public ShowScreenViewModelImpl(@UiScheduler Scheduler uiScheduler,
                                   InvestimentContract.View view) {
        this.uiScheduler = uiScheduler;
        this.view = view;
    }

    @Override
    public Observable<ScreenViewModel> call(Observable<ScreenViewModel> observable) {
        return observable
                .observeOn(uiScheduler)
                .subscribeOn(uiScheduler)
                .doOnNext(this::showViewModel);
    }

    private void showViewModel(ScreenViewModel viewModel) {
        view.showViewModel(viewModel);
    }
}
