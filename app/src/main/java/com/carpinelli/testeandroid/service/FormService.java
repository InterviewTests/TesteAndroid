package com.carpinelli.testeandroid.service;

import com.carpinelli.testeandroid.service.dto.CellSync;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FormService {

    @GET("cells.json")
    Call<CellSync> getCells();

}
