package com.seletiva.santander.investment.controllers.funds;

import android.support.annotation.NonNull;

import com.seletiva.santander.investment.R;
import com.seletiva.santander.investment.services.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FundsController {
    private FundsDataSource dataSource;
    private FundsResponse listener;

    public FundsController(FundsResponse responseListener) {
        dataSource = ApiClient.getInstance().create(FundsDataSource.class);
        listener = responseListener;
    }

    public void getCells() {
        Call<Object> call = dataSource.getFunds();

        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(@NonNull Call<Object> call, @NonNull Response<Object> response) {
                if(response.isSuccessful()) {
                    listener.onFundsResponse(response.body());
                } else {
                    listener.onRequestFailure(R.string.conn_error_cells);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Object> call, @NonNull Throwable t) {
                listener.onConnectionFailure();
            }
        });
    }
}