package com.meteoro.testeandroid.core.data.remote;

import com.meteoro.testeandroid.core.data.model.Cells;
import com.meteoro.testeandroid.core.data.model.ScreenVo;

import retrofit2.http.GET;
import rx.Observable;

public interface WebClientApi {

    @GET("/fund.json")
    Observable<ScreenVo> getFundInfo();

    @GET("/cells.json")
    Observable<Cells> getCells();
}
