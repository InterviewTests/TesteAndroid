package br.com.testeandroid.network;

import br.com.testeandroid.model.CellsResponse;
import br.com.testeandroid.model.Screen;
import br.com.testeandroid.model.Tela;
import retrofit2.Call;
import retrofit2.http.GET;

public interface NetworkUrl {

    @GET("fund.json")
    Call<Tela> getTelaInvestimento();

    @GET("cells.json")
    Call<CellsResponse> getCells();
}
