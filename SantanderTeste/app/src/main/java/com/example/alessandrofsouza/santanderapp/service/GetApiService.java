package com.example.alessandrofsouza.santanderapp.service;

import com.example.alessandrofsouza.santanderapp.model.ContactModel;
import com.example.alessandrofsouza.santanderapp.model.InvestmentModel;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class GetApiService {

    public static final String BASE_URL = "https://floating-mountain-50292.herokuapp.com";

    public interface ApiService {

        @GET("/cells.json")
        Call<ContactModel> listCells();

        @GET("/fund.json")
        Call<InvestmentModel> listInvestment();

    }

    public ApiService getApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ApiService.class);
    }

}
