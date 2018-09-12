package com.nataliafavero.santander.data.service;

import com.nataliafavero.santander.data.model.CellResponse;
import com.nataliafavero.santander.data.model.ScreenResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by nataliafavero on 10/09/18.
 */

public class ApiService {

    private String BASE_URL = "https://floating-mountain-50292.herokuapp.com/";

    public SantanderApi getApi() {
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(SantanderApi.class);
    }

    public interface SantanderApi {

        @GET("fund.json")
        Call<ScreenResponse> getFund();

        @GET("cells.json")
        Call<CellResponse> getCells();
    }
}
