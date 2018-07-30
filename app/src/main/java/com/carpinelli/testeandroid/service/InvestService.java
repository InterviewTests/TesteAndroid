package com.carpinelli.testeandroid.service;


import com.carpinelli.testeandroid.service.dto.InvestmentSync;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InvestService {

    @GET("fund.json")
    Call<InvestmentSync> getInvestment();

}
