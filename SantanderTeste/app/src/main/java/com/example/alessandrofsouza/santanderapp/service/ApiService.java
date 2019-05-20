package com.example.alessandrofsouza.santanderapp.service;

import com.example.alessandrofsouza.santanderapp.model.ContactModel;
import com.example.alessandrofsouza.santanderapp.model.InvestmentModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    public static final String BASE_URL = "https://floating-mountain-50292.herokuapp.com";

    @GET("/cells.json")
    Call<ContactModel> listCells();

    @GET("/fund.json")
    Call<InvestmentModel> listInvestment();

}
