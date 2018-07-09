package com.meteoro.testeandroid.ui.contact.presentation.coordinator;

import com.meteoro.testeandroid.core.data.model.Cells;
import com.meteoro.testeandroid.ui.contact.domain.interactor.GetCells;
import com.meteoro.testeandroid.ui.contact.domain.interactor.ShowLoadingContact;

import javax.inject.Inject;

import rx.Observable;

public class GetCellsCoordinator implements Observable.Transformer<String, Cells> {

    private ShowLoadingContact showLoadingContact;
    private GetCells getCells;

    @Inject
    public GetCellsCoordinator(ShowLoadingContact showLoadingContact,
                               GetCells getCells) {
        this.showLoadingContact = showLoadingContact;
        this.getCells = getCells;
    }

    @Override
    public Observable<Cells> call(Observable<String> observable) {
        return observable
                .compose(showLoadingContact)
                .compose(getCells);
    }
}
