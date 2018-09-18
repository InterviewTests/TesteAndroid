package br.com.santander.testeandroid.data.Funds;

import br.com.santander.testeandroid.ui.Funds.domain.Models.Funds;
import retrofit2.Call;
import retrofit2.http.GET;

public interface FundsService {
    @GET("/fund.json")
    Call<Funds> getFunds();
}
