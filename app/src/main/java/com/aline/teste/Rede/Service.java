package com.aline.teste.Rede;


import com.aline.teste.Models.Response;
import com.aline.teste.Models.Result;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {

    @GET("/cells.json")
    Call<Response> getContato();

    @GET("/fund.json")
    Call<Result> getFund();

}
