package com.avanade.santander.data.remote;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;

import com.avanade.santander.fundos.domain.model.Fundos;
import com.google.common.collect.Lists;

import java.util.LinkedHashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Implementation of the data source that adds a latency simulating network.
 */
public class FundosRemoteDataSource implements FundosDataSource {

    public static final String TAG = "FundosRemoteDS";

    /**
     * Singleton
     */
    private static FundosRemoteDataSource INSTANCE;

    private FundosRemoteDataSource() {
    }

    public static FundosRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FundosRemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public Fundos getFundos(final @NonNull LoadFundosCallback callback) {
        // Simulate network by delaying the execution.
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(()->{
            APIRetrofitService service =
                    ApiClient
                            .getClient(ApiClient.BASE_URL)
                            .create(APIRetrofitService.class);

            Call<Fundos> call = service.getFundos();
            call.enqueue(new Callback<Fundos>() {

                @Override
                public void onResponse(Call<Fundos> call, Response<Fundos> response) {
                    //Formulario formulario = response.body();
                    Log.e(TAG, "-------------------- RESPONSE: " + response.body().toString());

                    if (response.isSuccessful()) {
                        Log.i(TAG, "-------------------- RESPONSE: " + response.body());
                        Fundos fundos = response.body();
                        desenhaTela(fundos);
                    } else {
                        Log.d(TAG, "Erro: " + response.errorBody().toString());
                    }
                }

                @Override
                public void onFailure(Call<Fundos> call, Throwable t) {
                    // Log error here since request failed
                    Log.e(TAG, "Erro: " + t.toString());
                }
            });
        });


    }


}
