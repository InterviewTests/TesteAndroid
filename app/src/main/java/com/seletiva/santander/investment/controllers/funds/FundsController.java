package com.seletiva.santander.investment.controllers.funds;

import android.support.annotation.NonNull;

import com.seletiva.santander.investment.R;
import com.seletiva.santander.investment.services.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FundsController {
    private FundsDataSource dataSource;

    public FundsController() {
        dataSource = ApiClient.getInstance().create(FundsDataSource.class);
    }

    public void getFunds() {}
}