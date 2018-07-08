package com.meteoro.testeandroid.core.data.remote;

import com.meteoro.testeandroid.core.data.model.Screen;

import retrofit2.http.GET;
import rx.Observable;

public interface WebClientApi {

    @GET("/fund.json")
    Observable<Screen> getFundInfo();
}
