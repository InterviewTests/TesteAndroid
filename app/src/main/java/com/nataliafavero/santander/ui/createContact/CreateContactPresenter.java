package com.nataliafavero.santander.ui.createContact;

import android.support.annotation.NonNull;

import com.nataliafavero.santander.data.service.ApiService;
import com.nataliafavero.santander.ui.detailFund.DetailFundContract;

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
        //TODO call API
    }

}
