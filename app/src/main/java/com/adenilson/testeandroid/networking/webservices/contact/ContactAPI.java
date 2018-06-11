package com.adenilson.testeandroid.networking.webservices.contact;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * @author: Adenilson N. da Silva Junior
 * On date: 09/06/2018
 */

public interface ContactAPI {

    @Headers("Accept: application/json")
    @GET("cells.json")
    Call<CellsResponse> getCells();

}
