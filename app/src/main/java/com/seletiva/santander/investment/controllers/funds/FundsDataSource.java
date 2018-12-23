package com.seletiva.santander.investment.controllers.funds;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FundsDataSource {
    @GET("funds.json")
    Call<Object> getFunds();
}
