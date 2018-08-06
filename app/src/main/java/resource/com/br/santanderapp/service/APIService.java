package resource.com.br.santanderapp.service;

import resource.com.br.santanderapp.model.investmentModel.Investment;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    @GET("/fund.json")
    Call<Investment> getData();
}
