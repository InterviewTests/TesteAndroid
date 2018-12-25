package com.seletiva.santander.investment.datasources.funds;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FundsService {
    @GET("funds.json")
    Call<Object> getFunds();
}
