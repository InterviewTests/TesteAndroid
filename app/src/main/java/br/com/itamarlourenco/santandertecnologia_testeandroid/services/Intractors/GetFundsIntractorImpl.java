package br.com.itamarlourenco.santandertecnologia_testeandroid.services.Intractors;

import android.support.annotation.NonNull;

import br.com.itamarlourenco.santandertecnologia_testeandroid.app.App;
import br.com.itamarlourenco.santandertecnologia_testeandroid.app.ViewContract;
import br.com.itamarlourenco.santandertecnologia_testeandroid.model.Funds;
import br.com.itamarlourenco.santandertecnologia_testeandroid.services.FundServices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetFundsIntractorImpl implements ViewContract.GetIntractors {

    @Override
    public void getArrayList(final OnFinishedListener onFinishedListener) {
        FundServices cellServices = App.getRetrofitInstance().create(FundServices.class);
        Call<Funds.Screen> call = cellServices.getFundData();

        call.enqueue(new Callback<Funds.Screen>() {
            @Override
            public void onResponse(@NonNull Call<Funds.Screen> call, @NonNull Response<Funds.Screen> response) {
                if(response.body() != null){
                    onFinishedListener.onFinished(response.body().getScreen());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Funds.Screen> call, @NonNull Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });
    }
}
