package com.meteoro.testeandroid.ui.investiment.domain.interactor;

import com.meteoro.testeandroid.core.data.model.Screen;
import com.meteoro.testeandroid.core.data.remote.RepositoryApi;
import com.meteoro.testeandroid.core.di.qualifers.IoScheduler;
import com.meteoro.testeandroid.core.di.qualifers.UiScheduler;

import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;

public class GetFundImpl implements GetFund {

    private Scheduler ioScheduler;
    private Scheduler uiScheduler;
    private RepositoryApi repositoryApi;

    @Inject
    public GetFundImpl(@IoScheduler Scheduler ioScheduler,
                       @UiScheduler Scheduler uiScheduler,
                       RepositoryApi repositoryApi) {
        this.ioScheduler = ioScheduler;
        this.uiScheduler = uiScheduler;
        this.repositoryApi = repositoryApi;
    }

    @Override
    public Observable<Screen> call(Observable<String> observable) {
        return observable
                .flatMap(this::getScreen);
    }

    private Observable<Screen> getScreen(String ignore) {
        return repositoryApi.getFundInfo()
                .observeOn(uiScheduler)
                .subscribeOn(ioScheduler);
    }
}
