package br.com.ricardo.testeandroid.model.api;

import br.com.ricardo.testeandroid.model.FundApp;
import retrofit2.Call;
import retrofit2.http.GET;

public interface InvestmentService {

    public static final String BASE_URL = "https://floating-mountain-50292.herokuapp.com/";

    @GET("fund.json")
    Call<FundApp> listInfos();

}
