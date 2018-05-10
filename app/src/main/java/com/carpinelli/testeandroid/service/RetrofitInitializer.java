package com.carpinelli.testeandroid.service;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by rcarpinelli on 08/05/2018.
 */

public class RetrofitInitializer {

    private final String BASE_URL = "https://floating-mountain-50292.herokuapp.com/";

    private final Retrofit retrofit;

    public RetrofitInitializer() {
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public InvestService getInvestService() {
        return retrofit.create(InvestService.class);
    }

}
