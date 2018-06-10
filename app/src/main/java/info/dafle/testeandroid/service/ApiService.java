package info.dafle.testeandroid.service;

import info.dafle.testeandroid.model.ContatoResponse;
import info.dafle.testeandroid.model.Fund;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("/cells.json")
    Call<ContatoResponse> getCells();

    @GET("/fund.json")
    Call<Fund> getFund();
}
