package br.com.itamarlourenco.santandertecnologia_testeandroid.services.Intractors;

import android.support.annotation.NonNull;

import br.com.itamarlourenco.santandertecnologia_testeandroid.app.App;
import br.com.itamarlourenco.santandertecnologia_testeandroid.app.ViewContract;
import br.com.itamarlourenco.santandertecnologia_testeandroid.model.Cell;
import br.com.itamarlourenco.santandertecnologia_testeandroid.services.CellServices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetCellsIntractorImpl implements ViewContract.GetIntractors {

    @Override
    public void getArrayList(final OnFinishedListener onFinishedListener) {
        CellServices cellServices = App.getRetrofitInstance().create(CellServices.class);
        Call<Cell.Cells> call = cellServices.getCellData();

        call.enqueue(new Callback<Cell.Cells>() {
            @Override
            public void onResponse(@NonNull Call<Cell.Cells> call, @NonNull Response<Cell.Cells> response) {
                if(response.body() != null){
                    onFinishedListener.onFinished(response.body().getCells());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Cell.Cells> call, @NonNull Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });
    }
}
