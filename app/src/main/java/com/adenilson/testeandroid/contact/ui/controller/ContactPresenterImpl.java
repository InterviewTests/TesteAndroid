package com.adenilson.testeandroid.contact.ui.controller;

import com.adenilson.testeandroid.base.BasePresenter;
import com.adenilson.testeandroid.contact.ContactView;
import com.adenilson.testeandroid.contact.OnRequestContactListener;
import com.adenilson.testeandroid.contact.model.Cell;
import com.adenilson.testeandroid.contact.model.TypeEnum;
import com.adenilson.testeandroid.contact.model.mapper.CellMapper;
import com.adenilson.testeandroid.contact.ui.ContactInteractorImpl;
import com.adenilson.testeandroid.networking.webservices.contact.CellsResponse;

import java.util.List;

/**
 * @author: Adenilson N. da Silva Junior
 * On date: 10/06/2018
 */

public class ContactPresenterImpl implements BasePresenter<ContactView>, ContactPresenter, OnRequestContactListener {

    private ContactInteractorImpl mInteractor;
    private ContactView mView;

    public ContactPresenterImpl(ContactView view) {
        this.mView = view;
        this.mInteractor = new ContactInteractorImpl();
    }

    @Override
    public void attachView(ContactView view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
    }

    @Override
    public void getContact() {
        mView.showLoading();
        mInteractor.getContact(this);
    }

    @Override
    public void onRequestContactSuccess(CellsResponse response) {
        if (mView != null) {
            List<Cell> cells = new CellMapper().mapList(response);
            for (Cell cell : cells) {
                TypeEnum typeField = cell.getType();
                if(typeField == TypeEnum.TEXT){
                    mView.addTextView(cell);
                }else if(typeField == TypeEnum.FIELD){
                    mView.addEditText(cell);
                }else if(typeField == TypeEnum.CHECKBOX){
                    mView.addCheckbox(cell);
                }else if(typeField == TypeEnum.SEND){
                    mView.addButton(cell);
                }else if(typeField == TypeEnum.IMAGE){
                    mView.addImage(cell);
                }
            }
            mView.setSavedValues();
            mView.hideLoading();
        }

    }

    @Override
    public void onRequestContactFailed(int messageResourceId) {
        if (mView != null) {
            mView.showError(messageResourceId);
            mView.hideLoading();
        }
    }

    @Override
    public void sendMessage() {
        mView.showSuccessScreen();

    }
}
