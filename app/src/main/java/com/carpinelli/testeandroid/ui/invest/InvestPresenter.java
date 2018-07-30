package com.carpinelli.testeandroid.ui.invest;

import android.util.Log;
import android.view.View;

import com.carpinelli.testeandroid.service.InvestService;
import com.carpinelli.testeandroid.service.RetrofitInitializer;
import com.carpinelli.testeandroid.service.dto.InvestmentSync;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InvestPresenter implements MvpInvest.Presenter {

    private static final String TAG = InvestService.class.getSimpleName();

    private MvpInvest.View mvpView;

    public InvestPresenter(MvpInvest.View mvpView) {

        this.mvpView = mvpView;
    }

    @Override
    public void onStart() {

        if (mvpView != null) {

            Call<InvestmentSync> call = new RetrofitInitializer().getInvestService().getInvestment();

            call.enqueue(new Callback<InvestmentSync>() {
                @Override
                public void onResponse(Call<InvestmentSync> call, Response<InvestmentSync> response) {

                    mvpView.onInvestmentReady(response.body().getInvestment());
                }

                @Override
                public void onFailure(Call<InvestmentSync> call, Throwable t) {
                    Log.d(TAG, "onFailure: ");
                }
            });

        }
    }

    @Override
    public void onAttach(Object mvpView) {

    }

}
