package br.com.itamarlourenco.santandertecnologia_testeandroid.services;

import java.util.ArrayList;

import br.com.itamarlourenco.santandertecnologia_testeandroid.model.Cell;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CellServices {

    @GET("cells.json")
    Call<Cell.Cells> getCellData();

}
