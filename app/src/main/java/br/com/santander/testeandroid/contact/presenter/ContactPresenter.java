package br.com.santander.testeandroid.contact.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import br.com.santander.testeandroid.base.BasePresenter;
import br.com.santander.testeandroid.contact.ContactContract;
import br.com.santander.testeandroid.contact.ContactInteractor;
import br.com.santander.testeandroid.contact.model.CellsResponse;

public class ContactPresenter extends BasePresenter implements ContactInteractor.ContactListener {

    private ContactContract contractView;
    private ContactInteractor contactInteractor;

    public ContactPresenter(ContactContract contractView) {
        setContractView(contractView);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (contactInteractor == null)
            contactInteractor = new ContactInteractor();

        getContractView().showLoading();
        getContractView().hideError();
        contactInteractor.getCells(this);
    }

    @Override
    public void onGetCellsSuccess(CellsResponse response) {
        getContractView().hideLoading();
        getContractView().hideSuccess();
        getContractView().configureUI(response);
    }

    @Override
    public void onGetCellsFailure() {
        getContractView().hideLoading();
        getContractView().showError();
    }

    @Nullable
    private ContactContract getContractView() {
        return contractView;
    }

    private void setContractView(@NonNull ContactContract contractView) {
        this.contractView = contractView;
    }


}
