package com.avanade.santander.data.remote;

import com.avanade.santander.contato.domain.model.Cell;
import com.avanade.santander.fundos.domain.model.Fundos;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface APIRetrofitService {

    /* Retorna uma lista de objetos Cell */
    @GET("/cells.json")
    Call<List<Cell>> getCells();

    /* Retorna um objeto Fundos */
    @GET("/fund.json")
    Call<Fundos> getFundos();


}