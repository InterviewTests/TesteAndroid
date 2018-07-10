package com.meteoro.testeandroid.core.data.remote;

import com.meteoro.testeandroid.core.data.model.Cells;
import com.meteoro.testeandroid.core.data.model.ScreenVo;

import rx.Observable;

public interface RepositoryApi {
    Observable<ScreenVo> getFundInfo();

    Observable<Cells> getCells();
}
