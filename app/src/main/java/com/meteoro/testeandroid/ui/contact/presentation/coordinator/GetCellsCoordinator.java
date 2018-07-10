package com.meteoro.testeandroid.ui.contact.presentation.coordinator;

import com.meteoro.testeandroid.ui.contact.domain.interactor.ConvertToCellsViewModel;
import com.meteoro.testeandroid.ui.contact.domain.interactor.GetCells;
import com.meteoro.testeandroid.ui.contact.domain.interactor.ShowCellsViewModel;
import com.meteoro.testeandroid.ui.contact.domain.interactor.ShowLoadingContact;
import com.meteoro.testeandroid.ui.contact.domain.model.CellsViewModel;

import javax.inject.Inject;

import rx.Observable;

public class GetCellsCoordinator implements Observable.Transformer<String, CellsViewModel> {

    private ShowLoadingContact showLoadingContact;
    private GetCells getCells;
    private ConvertToCellsViewModel convertToCellsViewModel;
    private ShowCellsViewModel showCellsViewModel;

    @Inject
    public GetCellsCoordinator(ShowLoadingContact showLoadingContact,
                               GetCells getCells,
                               ConvertToCellsViewModel convertToCellsViewModel,
                               ShowCellsViewModel showCellsViewModel) {
        this.showLoadingContact = showLoadingContact;
        this.getCells = getCells;
        this.convertToCellsViewModel = convertToCellsViewModel;
        this.showCellsViewModel = showCellsViewModel;
    }

    @Override
    public Observable<CellsViewModel> call(Observable<String> observable) {
        return observable
                .compose(showLoadingContact)
                .compose(getCells)
                .compose(convertToCellsViewModel)
                .compose(showCellsViewModel);
    }
}
