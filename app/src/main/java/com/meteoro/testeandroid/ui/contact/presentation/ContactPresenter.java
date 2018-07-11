package com.meteoro.testeandroid.ui.contact.presentation;

import com.meteoro.testeandroid.core.lifecycle.AutomaticUnsubscriber;
import com.meteoro.testeandroid.ui.contact.domain.model.CellsViewModel;
import com.meteoro.testeandroid.ui.contact.presentation.coordinator.GetCellsCoordinator;
import com.meteoro.testeandroid.ui.contact.presentation.coordinator.ValidateCoordinator;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;

public class ContactPresenter implements ContactContract.Presenter {

    private GetCellsCoordinator getCellsCoordinator;
    private ValidateCoordinator validateCoordinator;
    private AutomaticUnsubscriber automaticUnsubscriber;

    @Inject
    public ContactPresenter(GetCellsCoordinator getCellsCoordinator,
                            ValidateCoordinator validateCoordinator,
                            AutomaticUnsubscriber automaticUnsubscriber) {
        this.getCellsCoordinator = getCellsCoordinator;
        this.validateCoordinator = validateCoordinator;
        this.automaticUnsubscriber = automaticUnsubscriber;
    }

    @Override
    public void initializeContents() {
        Subscription subscription =
                Observable.just("")
                        .compose(getCellsCoordinator)
                        .subscribe();
        automaticUnsubscriber.add(subscription);
    }

    @Override
    public void validateFields(CellsViewModel viewModel) {
        Subscription subscription =
                Observable.just(viewModel)
                        .compose(validateCoordinator)
                        .subscribe();
        automaticUnsubscriber.add(subscription);
    }
}
