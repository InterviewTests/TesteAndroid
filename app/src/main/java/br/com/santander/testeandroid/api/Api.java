package br.com.santander.testeandroid.api;

import br.com.santander.testeandroid.contact.model.CellsResponse;
import br.com.santander.testeandroid.investment.model.InvestmentResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Interface to map API calls contract.
 */
public interface Api {

    String BASE_URL = "https://floating-mountain-50292.herokuapp.com/";

    /**
     * Retrieve investment funds data
     */
    @GET("/fund.json")
    Call<InvestmentResponse> getFunds();

    /**
     * Retrieve cells data
     */
    @GET("/cells.json")
    Call<CellsResponse> getCells();

}
