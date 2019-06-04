package com.example.alessandrofsouza.santanderapp.presentation.pages.investment0;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.alessandrofsouza.santanderapp.R;
import com.example.alessandrofsouza.santanderapp.data.service.ApiService;
import com.example.alessandrofsouza.santanderapp.domain.model.Infos;
import com.example.alessandrofsouza.santanderapp.domain.model.InvestmentModel;
import com.example.alessandrofsouza.santanderapp.domain.model.MoreInfo;
import com.example.alessandrofsouza.santanderapp.domain.model.Screen;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InvestmentPresenter0 implements InvestmentContract0.Presenter{

    private static final String TAG = "Santander ";
    private InvestmentContract0.View view;
    private ApiService service;

    public InvestmentPresenter0(@NonNull InvestmentContract0.View investmentView) {
        view = investmentView;
        view.setPresenter(this);
        service = new ApiService();
    }

    @Override
    public void init() {
        getInvestmentScreen();
    }

    @Override
    public void getInvestmentScreen() {
        service.getApi().listInvestment().enqueue(new Callback<InvestmentModel>() {
            @NonNull
            @Override
            public void onResponse(Call<InvestmentModel> call, Response<InvestmentModel> response) {
                if (response.isSuccessful()) {
                    InvestmentModel investmentModel = response.body();

                    view.showInvestmentScreen(investmentModel.getScreen());
                } else {
                    Log.e(TAG, "Error Unsuccessful " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<InvestmentModel> call, Throwable t) {
                Log.e(TAG, "Error Failure " + t.getMessage());
            }
        });
    }

}
