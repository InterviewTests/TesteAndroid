package com.avanade.santander.data.remote;


import android.support.annotation.NonNull;

import com.avanade.santander.contato.domain.model.Cell;
import com.avanade.santander.contato.domain.model.Formulario;
import com.avanade.santander.data.CellsDataSource;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CellsRemoteDataSource implements CellsDataSource {

    public static final String TAG = "CellsRemoteDS";

    /**
     * Singleton
     */
    private static CellsRemoteDataSource INSTANCE;
    APIRetrofitService apiRetrofitService;

    private CellsRemoteDataSource() {
        apiRetrofitService = ApiClient
                .getClient(ApiClient.BASE_URL)
                .create(APIRetrofitService.class);
    }

    public static CellsRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CellsRemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void getFormulario(@NonNull LoadCellsCallback callback) {

        Call<Formulario> call = apiRetrofitService.getFormulario();
        call.enqueue(
                new Callback<Formulario>() {

                    @Override
                    public void onResponse(Call<Formulario> call, Response<Formulario> response) {
                        //Log.d(TAG, "-------------------- RESPONSE: " + response.body().toString());

                        if (response.isSuccessful()) {
                            //Log.d(TAG, "-------------------- RESPONSE: " + response.body());
                            Formulario cells = response.body();
                            callback.onCellsLoaded(cells);

                        } else {
                            //Log.d(TAG, "Erro: " + response.errorBody().toString());
                            callback.onDataNotAvailable();
                        }
                    }

                    @Override
                    public void onFailure(Call<Formulario> call, Throwable t) {
                        // Log error here since request failed
                        //Log.e(TAG, "Erro: " + t.toString());
                        callback.onDataNotAvailable();
                    }
                }
        );
    }

    @Override
    public void refreshCells(List<Cell> cells) {
        // TODO - devemos implementar para se for utilizar Post update do Formulário para a API
    }


}
