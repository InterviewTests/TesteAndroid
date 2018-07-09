package com.meteoro.testeandroid.ui.contact.presentation.coordinator;

import com.meteoro.testeandroid.ui.contact.domain.interactor.ShowLoadingContact;

import javax.inject.Inject;

import rx.Observable;

public class GetCellsCoordinator implements Observable.Transformer<String, String> {

    private ShowLoadingContact showLoadingContact;

    @Inject
    public GetCellsCoordinator(ShowLoadingContact showLoadingContact) {
        this.showLoadingContact = showLoadingContact;
    }

    @Override
    public Observable<String> call(Observable<String> observable) {
        return observable
                .compose(showLoadingContact);
    }
}
