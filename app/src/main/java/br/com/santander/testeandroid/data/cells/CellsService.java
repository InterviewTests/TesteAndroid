package br.com.santander.testeandroid.data.cells;

import br.com.santander.testeandroid.ui.contact.domain.models.CellsList;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CellsService {
    @GET("/cells.json")
    Call<CellsList> getCells();
}
