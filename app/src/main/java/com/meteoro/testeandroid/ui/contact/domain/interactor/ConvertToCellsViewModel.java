package com.meteoro.testeandroid.ui.contact.domain.interactor;

import com.meteoro.testeandroid.core.data.model.Cells;
import com.meteoro.testeandroid.ui.contact.domain.model.CellsViewModel;

import rx.Observable;

public interface ConvertToCellsViewModel extends Observable.Transformer<Cells, CellsViewModel> {
}
