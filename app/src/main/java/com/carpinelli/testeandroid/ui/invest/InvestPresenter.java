package com.carpinelli.testeandroid.ui.invest;

import android.util.Log;
import android.view.View;

import com.carpinelli.testeandroid.model.Screen;
import com.carpinelli.testeandroid.service.InvestService;
import com.carpinelli.testeandroid.service.RetrofitInitializer;
import com.carpinelli.testeandroid.service.dto.ScreenSync;

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

            Call<ScreenSync> call = new RetrofitInitializer().getInvestService().getScreen();

            call.enqueue(new Callback<ScreenSync>() {
                @Override
                public void onResponse(Call<ScreenSync> call, Response<ScreenSync> response) {

                    mvpView.onScreenReady(response.body().getScreen());

                }

                @Override
                public void onFailure(Call<ScreenSync> call, Throwable t) {
                    Log.d(TAG, "onFailure: ");
                }
            });

        }

    }

    @Override
    public View.OnClickListener onInvestment() {
        return null;
    }

    @Override
    public void onAttach(Object mvpView) {

    }


}
