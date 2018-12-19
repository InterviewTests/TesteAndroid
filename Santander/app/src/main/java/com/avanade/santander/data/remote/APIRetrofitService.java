package com.avanade.santander.data.remote;

import com.avanade.santander.contato.domain.model.Formulario;
import com.avanade.santander.fundos.domain.model.Fundos;


import retrofit2.Call;
import retrofit2.http.GET;


public interface APIRetrofitService {

    /* Retorna uma lista de objetos Cell */
    @GET("/cells.json")
    Call<Formulario> getFormulario();

    /* Retorna um objeto Fundos */
    @GET("/fund.json")
    Call<Fundos> getFundos();


}