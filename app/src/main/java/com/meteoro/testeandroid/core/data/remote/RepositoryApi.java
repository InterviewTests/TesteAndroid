package com.meteoro.testeandroid.core.data.remote;

import com.meteoro.testeandroid.core.data.model.Screen;

import rx.Observable;

public interface RepositoryApi {
    Observable<Screen> getFundInfo();
}
