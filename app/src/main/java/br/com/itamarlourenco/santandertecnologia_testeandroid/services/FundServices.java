package br.com.itamarlourenco.santandertecnologia_testeandroid.services;

import br.com.itamarlourenco.santandertecnologia_testeandroid.model.Cell;
import br.com.itamarlourenco.santandertecnologia_testeandroid.model.Funds;
import retrofit2.Call;
import retrofit2.http.GET;

public interface FundServices {

    @GET("fund.json")
    Call<Funds.Screen> getFundData();

}
