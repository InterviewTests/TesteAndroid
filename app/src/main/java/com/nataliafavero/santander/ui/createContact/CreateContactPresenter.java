package com.nataliafavero.santander.ui.createContact;

import android.support.annotation.NonNull;

import com.nataliafavero.santander.data.model.CellResponse;
import com.nataliafavero.santander.data.service.ApiService;
import com.nataliafavero.santander.ui.detailFund.DetailFundContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nataliafavero on 10/09/18.
 */

public class CreateContactPresenter implements CreateContactContract.Presenter {

    private final CreateContactContract.View mView;
    private final ApiService apiService;

    public CreateContactPresenter(@NonNull CreateContactContract.View view) {
        mView = view;
        mView.setPresenter(this);
        apiService = new ApiService();
    }

    @Override
    public void start() {
        getCells();
    }

    @Override
    public void getCells() {
        apiService.getApi().getCells().enqueue(new Callback<CellResponse>() {
            @Override
            public void onResponse(Call<CellResponse> call, Response<CellResponse> response) {
                if (response.isSuccessful()) {
                    mView.showCells(response.body().getCells());
                }
            }

            @Override
            public void onFailure(Call<CellResponse> call, Throwable t) {
                //TODO error
            }
        });
    }

}
