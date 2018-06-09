package info.dafle.testeandroid.service;

import java.util.List;

import info.dafle.testeandroid.model.Cell;
import info.dafle.testeandroid.model.Fund;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("/cells.json")
    Call<List<Cell>> getCells();

    @GET("/fund.json")
    Call<Fund> getFund();
}
