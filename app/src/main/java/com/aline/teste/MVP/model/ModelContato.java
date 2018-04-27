package com.aline.teste.MVP.model;


import android.util.Log;

import com.aline.teste.MVP.MVP;
import com.aline.teste.Models.Cells;
import com.aline.teste.Models.Response;
import com.aline.teste.Rede.RetrofitConfig;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class ModelContato implements MVP.ModelCont {

    private static final String LOG = ModelContato.class.getSimpleName();
    private MVP.PresenterCont presenter;

    public ModelContato(MVP.PresenterCont presenter){
        this.presenter = presenter;
    }
    @Override
    public void callRetrofitContato() {

        try {
            Call<Response> call = new RetrofitConfig().getService().getContato();
            call.enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                    if(response.isSuccessful()){
                        Response responseResult = response.body();
                        List<Cells> cellsList = responseResult.getCells();
                        presenter.updateCells(cellsList);
                    }
                }

                @Override
                public void onFailure(Call<Response> call, Throwable t) {
                    Log.e(LOG, "Error: " + t.getMessage());
                }
            });
        }catch (Exception e){
            Log.e(LOG, "Error: " + e.getMessage());
        }
    }
}
