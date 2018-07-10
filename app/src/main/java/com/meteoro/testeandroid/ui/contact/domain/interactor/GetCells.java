package com.meteoro.testeandroid.ui.contact.domain.interactor;

import com.meteoro.testeandroid.core.data.model.Cells;

import rx.Observable;

public interface GetCells extends Observable.Transformer<String, Cells> {
}
