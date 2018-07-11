package com.meteoro.testeandroid.ui.contact.presentation.coordinator;

import com.meteoro.testeandroid.ui.contact.domain.interactor.ShowResultValidate;
import com.meteoro.testeandroid.ui.contact.domain.interactor.ValidateFields;
import com.meteoro.testeandroid.ui.contact.domain.model.CellsViewModel;

import javax.inject.Inject;

import rx.Observable;

public class ValidateCoordinator implements Observable.Transformer<CellsViewModel, CellsViewModel> {

    private ValidateFields validateFields;
    private ShowResultValidate showResultValidate;

    @Inject
    public ValidateCoordinator(ValidateFields validateFields,
                               ShowResultValidate showResultValidate) {
        this.validateFields = validateFields;
        this.showResultValidate = showResultValidate;
    }

    @Override
    public Observable<CellsViewModel> call(Observable<CellsViewModel> observable) {
        return observable
                .compose(validateFields)
                .compose(showResultValidate);
    }
}
