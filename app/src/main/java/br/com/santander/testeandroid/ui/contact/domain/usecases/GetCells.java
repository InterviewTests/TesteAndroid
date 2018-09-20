package br.com.santander.testeandroid.ui.contact.domain.usecases;

import br.com.santander.testeandroid.data.RetrofitConfiguration;
import br.com.santander.testeandroid.data.cells.CellsService;
import br.com.santander.testeandroid.ui.contact.domain.models.CellsList;
import retrofit2.Call;
import retrofit2.Callback;

public class GetCells {
    public static void getCells(Callback<CellsList> callback) {
        CellsService service = (CellsService) RetrofitConfiguration.getService(CellsService.class);
        Call<CellsList> getCells = service.getCells();
        getCells.enqueue(callback);
    }
}
