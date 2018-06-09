package com.adenilson.testeandroid.networking.webservices.investiment;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * @author: Adenilson N. da Silva Junior
 * On date: 09/06/2018
 */

public interface InvestimentAPI {

    @Headers("Accept: application/json")
    @GET("fund.json")
    Call<InvestmentResponse> getFund();

}
