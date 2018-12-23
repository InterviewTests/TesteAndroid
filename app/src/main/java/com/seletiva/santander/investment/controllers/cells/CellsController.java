package com.seletiva.santander.investment.controllers.cells;

import android.support.annotation.NonNull;

import com.seletiva.santander.investment.ui.form.domain.model.CellHolder;
import com.seletiva.santander.investment.R;
import com.seletiva.santander.investment.services.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CellsController {
    private CellsDataSource dataSource;
    private CellResponse listener;

    public CellsController(CellResponse responseListener) {
        dataSource = ApiClient.getInstance().create(CellsDataSource.class);
        listener = responseListener;
    }

    public void getCells() {
        Call<CellHolder> call = dataSource.getCells();

        call.enqueue(new Callback<CellHolder>() {
            @Override
            public void onResponse(@NonNull Call<CellHolder> call, @NonNull Response<CellHolder> response) {
                if(response.isSuccessful()) {
                    listener.onCellsResponse(response.body());
                } else {
                    listener.onRequestFailure(R.string.conn_error_cells);
                }
            }

            @Override
            public void onFailure(@NonNull Call<CellHolder> call, @NonNull Throwable t) {
                listener.onConnectionFailure();
            }
        });
    }
}
