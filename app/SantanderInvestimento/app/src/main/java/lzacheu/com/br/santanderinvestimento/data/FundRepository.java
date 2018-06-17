package lzacheu.com.br.santanderinvestimento.data;

import android.support.annotation.NonNull;
import android.util.Log;

import lzacheu.com.br.santanderinvestimento.data.remote.ApiClient;
import lzacheu.com.br.santanderinvestimento.data.remote.fund.FundResponse;
import lzacheu.com.br.santanderinvestimento.data.remote.fund.FundService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by luiszacheu on 6/16/18.
 */

public class FundRepository implements FundDataSource {

    private static final String LOG_TAG = FundRepository.class.getName();
    private FundService service;


    public FundRepository() {
        service = ApiClient.getRetrofitInstance().create(FundService.class);
    }


    @Override
    public void getFunds(final LoadFundCallback callback) {
        Call<FundResponse> fundResponseCall = service.getFund();
        fundResponseCall.enqueue(new Callback<FundResponse>() {
            @Override
            public void onResponse(@NonNull  Call<FundResponse> call, @NonNull Response<FundResponse> response) {
                callback.onFundsLoaded(response.body().getScreen());
                Log.e(LOG_TAG, "->" + response.body().getScreen().toString());
            }

            @Override
            public void onFailure(Call<FundResponse> call, Throwable t) {
                Log.e(LOG_TAG, " Ocorreu alguma falha" );
            }
        });
    }
}
