package com.nataliafavero.santander.ui.detailFund;

import android.support.annotation.NonNull;

import com.nataliafavero.santander.data.model.CellResponse;
import com.nataliafavero.santander.data.model.ScreenResponse;
import com.nataliafavero.santander.data.service.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nataliafavero on 11/09/18.
 */

public class DetailFundPresenter implements DetailFundContract.Presenter {

    private final DetailFundContract.View mView;
    private final ApiService apiService;

    public DetailFundPresenter(@NonNull DetailFundContract.View view) {
        mView = view;
        mView.setPresenter(this);
        apiService = new ApiService();
    }

    @Override
    public void start() {

    }

    @Override
    public void getFund() {
        apiService.getApi().getFund().enqueue(new Callback<ScreenResponse>() {
            @Override
            public void onResponse(Call<ScreenResponse> call, Response<ScreenResponse> response) {
                if (response.isSuccessful()) {
                    mView.showFund();
                }
            }

            @Override
            public void onFailure(Call<ScreenResponse> call, Throwable t) {

            }
        });
    }
}
