package br.com.santander.testeandroid.data.funds;

import br.com.santander.testeandroid.ui.funds.domain.models.Funds;
import retrofit2.Call;
import retrofit2.http.GET;

public interface FundsService {
    @GET("/fund.json")
    Call<Funds> getFunds();
}
