package com.avanade.santander.data.remote;

import android.support.annotation.NonNull;
import android.util.Log;

import com.avanade.santander.data.FundosDataSource;
import com.avanade.santander.fundos.domain.model.Fundos;

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
    APIRetrofitService apiRetrofitService;

    private FundosRemoteDataSource() {
        apiRetrofitService = ApiClient
                        .getClient(ApiClient.BASE_URL)
                        .create(APIRetrofitService.class);
    }

    public static FundosRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FundosRemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void getFundos(final @NonNull LoadFundosCallback callback) {
        // Simulate network by delaying the execution.

        Call<Fundos> call = apiRetrofitService.getFundos();
        call.enqueue(
                new Callback<Fundos>() {

                    @Override
                    public void onResponse(Call<Fundos> call, Response<Fundos> response) {
                        //Log.e(TAG, "-------------------- RESPONSE: " + response.body().toString());

                        if (response.isSuccessful()) {
                            //Log.d(TAG, "-------------------- RESPONSE: " + response.body());
                            Fundos fundos = response.body();
                            callback.onFundosLoaded(fundos);

                        } else {
                            Log.d(TAG, "Erro: " + response.errorBody().toString());
                            callback.onDataNotAvailable();
                        }
                    }

                    @Override
                    public void onFailure(Call<Fundos> call, Throwable t) {
                        // Log error here since request failed
                        Log.e(TAG, "Erro: " + t.toString());
                        callback.onDataNotAvailable();
                    }
                }
        );


    }


}
