package br.com.ricardo.testeandroid.model;

import br.com.ricardo.testeandroid.model.api.InvestmentService;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InvestmentInteractorImpl implements InvestmentInteractor {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(InvestmentService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    InvestmentService service = retrofit.create(InvestmentService.class);

    @Override
    public Call<FundApp> requestFundInfos() {
        return service.listInfos();
    }
}
