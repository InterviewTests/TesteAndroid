package com.meteoro.testeandroid.ui.contact.domain.interactor;

import com.meteoro.testeandroid.ui.contact.domain.model.CellsViewModel;

import rx.Observable;

public interface ShowCellsViewModel extends Observable.Transformer<CellsViewModel, CellsViewModel> {
}
