package br.com.santander.testeandroid.investment;

import android.support.annotation.NonNull;

import br.com.santander.testeandroid.api.APIServiceFactory;
import br.com.santander.testeandroid.investment.model.InvestmentResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InvestmentInteractor {

    public interface InvestmentListener {
        void onGetFoundSuccess(InvestmentResponse response);

        void onGetFoundFailure();
    }

    public void getFunds(final InvestmentListener listener) {
        APIServiceFactory.getInstance()
                .getFunds()
                .enqueue(new Callback<InvestmentResponse>() {

                    @Override
                    public void onResponse(@NonNull Call<InvestmentResponse> call, @NonNull Response<InvestmentResponse> response) {
                        listener.onGetFoundSuccess(response.body());
                    }

                    @Override
                    public void onFailure(@NonNull Call<InvestmentResponse> call, @NonNull Throwable t) {
                        listener.onGetFoundFailure();
                    }
                });

    }

}