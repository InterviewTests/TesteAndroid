package com.meteoro.testeandroid.ui.contact.presentation.coordinator;

import com.meteoro.testeandroid.ui.contact.domain.interactor.ConvertToCellsViewModel;
import com.meteoro.testeandroid.ui.contact.domain.interactor.GetCells;
import com.meteoro.testeandroid.ui.contact.domain.interactor.ShowLoadingContact;
import com.meteoro.testeandroid.ui.contact.domain.model.CellsViewModel;

import javax.inject.Inject;

import rx.Observable;

public class GetCellsCoordinator implements Observable.Transformer<String, CellsViewModel> {

    private ShowLoadingContact showLoadingContact;
    private GetCells getCells;
    private ConvertToCellsViewModel convertToCellsViewModel;

    @Inject
    public GetCellsCoordinator(ShowLoadingContact showLoadingContact,
                               GetCells getCells,
                               ConvertToCellsViewModel convertToCellsViewModel) {
        this.showLoadingContact = showLoadingContact;
        this.getCells = getCells;
        this.convertToCellsViewModel = convertToCellsViewModel;
    }

    @Override
    public Observable<CellsViewModel> call(Observable<String> observable) {
        return observable
                .compose(showLoadingContact)
                .compose(getCells)
                .compose(convertToCellsViewModel);
    }
}
