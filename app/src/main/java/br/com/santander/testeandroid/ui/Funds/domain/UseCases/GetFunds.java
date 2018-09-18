package br.com.santander.testeandroid.ui.Funds.domain.UseCases;

import br.com.santander.testeandroid.data.Funds.FundsService;
import br.com.santander.testeandroid.data.RetrofitConfiguration;
import br.com.santander.testeandroid.ui.Funds.domain.Models.Funds;
import retrofit2.Call;
import retrofit2.Callback;

public class GetFunds {
    public static void getFunds(Callback<Funds> callback) {
        FundsService service = (FundsService) RetrofitConfiguration.getService(FundsService.class);
        Call<Funds> getFunds = service.getFunds();
        getFunds.enqueue(callback);
    }
}
