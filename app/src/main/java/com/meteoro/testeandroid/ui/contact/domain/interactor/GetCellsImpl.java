package com.meteoro.testeandroid.ui.contact.domain.interactor;

import com.meteoro.testeandroid.core.data.model.Cells;
import com.meteoro.testeandroid.core.data.remote.RepositoryApi;
import com.meteoro.testeandroid.core.di.qualifers.IoScheduler;
import com.meteoro.testeandroid.core.di.qualifers.UiScheduler;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;

public class GetCellsImpl implements GetCells {

    private Scheduler ioScheduler;
    private Scheduler uiScheduler;
    private RepositoryApi repositoryApi;

    @Inject
    public GetCellsImpl(@IoScheduler Scheduler ioScheduler,
                        @UiScheduler Scheduler uiScheduler,
                        RepositoryApi repositoryApi) {
        this.ioScheduler = ioScheduler;
        this.uiScheduler = uiScheduler;
        this.repositoryApi = repositoryApi;
    }

    @Override
    public Observable<Cells> call(Observable<String> observable) {
        return observable
                .flatMap(this::getCells);
    }

    private Observable<Cells> getCells(String ignore) {
        return repositoryApi.getCells()
                .observeOn(uiScheduler)
                .subscribeOn(ioScheduler);
    }
}
