package com.carpinelli.testeandroid.ui.form;

import android.util.Log;

import com.carpinelli.testeandroid.service.InvestService;
import com.carpinelli.testeandroid.service.RetrofitInitializer;
import com.carpinelli.testeandroid.service.dto.CellSync;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormPresenter implements MvpForm.Presenter {

    private static final String TAG = InvestService.class.getSimpleName();

    private MvpForm.View formView;

    public FormPresenter(MvpForm.View formView) {
        this.formView = formView;
    }

    @Override
    public void onStart() {

        if (formView != null) {
            Call<CellSync> call = new RetrofitInitializer().getFormService().getCells();

            call.enqueue(new Callback<CellSync>() {
                @Override
                public void onResponse(Call<CellSync> call, Response<CellSync> response) {

                    formView.onCellsReady(response.body().getCells());
                }

                @Override
                public void onFailure(Call<CellSync> call, Throwable t) {

                    Log.d(TAG, "onFailure: ");
                }
            });

        }

    }

    @Override
    public void onFormCompleted() {

        formView.onSendForm();
    }


    @Override
    public void onAttach(Object mvpView) {

    }
}
