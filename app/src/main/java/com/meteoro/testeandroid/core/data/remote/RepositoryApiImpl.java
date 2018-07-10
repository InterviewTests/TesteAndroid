package com.meteoro.testeandroid.core.data.remote;

import com.meteoro.testeandroid.core.client.ApiClientUtil;
import com.meteoro.testeandroid.core.data.model.Cells;
import com.meteoro.testeandroid.core.data.model.ScreenVo;

import rx.Observable;

public class RepositoryApiImpl implements RepositoryApi {

    private final WebClientApi api;

    public RepositoryApiImpl(String host) {
        api = new ApiClientUtil.Builder()
                .log(true)
                .url(host)
                .build()
                .create(WebClientApi.class);
    }

    @Override
    public Observable<ScreenVo> getFundInfo() {
        return api.getFundInfo();
    }

    @Override
    public Observable<Cells> getCells() {
        return api.getCells();
    }
}
