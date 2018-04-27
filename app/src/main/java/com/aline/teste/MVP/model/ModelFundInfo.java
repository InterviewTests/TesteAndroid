package com.aline.teste.MVP.model;

import android.util.Log;

import com.aline.teste.MVP.MVP;
import com.aline.teste.Models.Result;
import com.aline.teste.Models.Screen;
import com.aline.teste.Rede.RetrofitConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ModelFundInfo implements MVP.ModelFund {

    private static final String LOG = MVP.ModelFund.class.getSimpleName();
    private MVP.PresenterFund presenterFund;

    public ModelFundInfo(MVP.PresenterFund presenterFund){
        this.presenterFund = presenterFund;
    }

    @Override
    public void callRetrofitFund() {

        try{
            Call<Result> call = new RetrofitConfig().getService().getFund();
            call.enqueue(new Callback<Result>() {
                @Override
                public void onResponse(Call<Result> call, Response<Result> response) {
                    if(response.isSuccessful()){
                        Result result = response.body();
                        Screen screen = result.getScreen();
                        presenterFund.updateFund(screen);
                    }
                }
                @Override
                public void onFailure(Call<Result> call, Throwable t) {
                    Log.e(LOG, "Error: " + t.getMessage());
                }
            });

        }   catch (Exception e ){
            Log.e(LOG, "Error: " + e.getMessage());
        }
    }
}
