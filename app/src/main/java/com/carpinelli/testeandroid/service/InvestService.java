package com.carpinelli.testeandroid.service;


import com.carpinelli.testeandroid.model.Screen;
import com.carpinelli.testeandroid.service.dto.ScreenSync;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InvestService {

    @GET("fund.json")
    Call<ScreenSync> getScreen();

}
