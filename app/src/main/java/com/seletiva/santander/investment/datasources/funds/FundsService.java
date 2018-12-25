package com.seletiva.santander.investment.datasources.funds;

import com.seletiva.santander.investment.ui.investments.domain.Funds;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface FundsService {
    @GET("fund.json")
    Observable<Funds> getFunds();
}
