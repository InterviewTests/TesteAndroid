package com.meteoro.testeandroid.ui.contact.domain.interactor;

import com.meteoro.testeandroid.core.di.qualifers.IoScheduler;
import com.meteoro.testeandroid.core.di.qualifers.UiScheduler;
import com.meteoro.testeandroid.ui.contact.domain.model.CellsViewModel;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;

public class ValidateFieldsImpl implements ValidateFields {

    private Scheduler ioScheduler;
    private Scheduler uiScheduler;

    @Inject
    public ValidateFieldsImpl(@IoScheduler Scheduler ioScheduler,
                              @UiScheduler Scheduler uiScheduler) {
        this.ioScheduler = ioScheduler;
        this.uiScheduler = uiScheduler;
    }

    @Override
    public Observable<CellsViewModel> call(Observable<CellsViewModel> observable) {
        return null;
    }
}
