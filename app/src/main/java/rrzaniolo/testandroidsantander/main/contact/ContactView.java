package rrzaniolo.testandroidsantander.main.contact;

/*
 * Created by rrzaniolo on 08/05/18.
 * Copyright © 2018 rrzaniolo. All rights reserved.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import rrzaniolo.testandroidsantander.R;
import rrzaniolo.testandroidsantander.base.BaseInnerView;
import rrzaniolo.testandroidsantander.main.contact.Custom.ContactLayout;

/**
 * Implementation for the Contact View.
 * */
public class ContactView extends BaseInnerView implements ContactContract.View{

    //region --- Variables
    private static ContactView instance;
    public static ContactView getInstace(){
        if(instance == null)
            instance = new ContactView();

        return instance;
    }

    private ContactContract.Presenter contractPresenter;
    public @Nullable ContactContract.Presenter getContractPresenter() {
        return contractPresenter;
    }
    public void setContractPresenter(@NonNull ContactContract.Presenter contractPresenter) {
        this.contractPresenter = contractPresenter;
    }

    private View rootView;
    public View getRootView() {
        return rootView;
    }
    public void setRootView(View rootView) {
        this.rootView = rootView;
    }
    //endregion

    //region --- Constructor
    public ContactView() {
        setContractPresenter(new ContactPresenter(ContactView.this));
    }
    //endregion

    //region --- LifeCycle
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setRootView(inflater.inflate(R.layout.inner_view_contact, container, false));
        return getRootView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(getContractPresenter() != null) {
            getRootView().findViewById(R.id.lSuccess_tvSend).setOnClickListener(getContractPresenter().onSendNewMessageClick());
            getContractPresenter().onStart();
        }
    }
    //endregion

    //region --- Private Methods

    //region --- Contract Methods
    @Override
    public ContactLayout getForm() {
        return getRootView().findViewById(R.id.fContact_clForm);
    }

    @Override
    public void setFormListener() {
        if(getContractPresenter() != null)
            ((ContactLayout)getRootView().findViewById(R.id.fContact_clForm)).setButtonListener(
                    getContractPresenter().onSendEventClick()
            );
    }

    @Override
    public void clearForm() {
        ((ContactLayout)getRootView().findViewById(R.id.fContact_clForm)).clearFields();
    }

    @Override
    public Boolean isFromWithError() {
        return ((ContactLayout)getRootView().findViewById(R.id.fContact_clForm)).checkErrors();
    }

    @Override
    public void showFormErrors() {
        hideFormErrors();
        ((ContactLayout)getRootView().findViewById(R.id.fContact_clForm)).showErrors();
    }

    @Override
    public void hideFormErrors() {
        ((ContactLayout)getRootView().findViewById(R.id.fContact_clForm)).hideErrors();
    }

    @Override
    public void showSuccess() {
        getRootView().findViewById(R.id.fContact_sv).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideSuccess() {
        getRootView().findViewById(R.id.fContact_sv).setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        getRootView().findViewById(R.id.fContact_lv).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        getRootView().findViewById(R.id.fContact_lv).setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        getRootView().findViewById(R.id.fContact_ev).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideError() {
        getRootView().findViewById(R.id.fContact_ev).setVisibility(View.GONE);
    }

    @Override
    public void hideSoftKeyboard() {
        View view = getRootView().findFocus();
        if (view != null && getBaseView() != null) {
            InputMethodManager imm = (InputMethodManager)getBaseView().getSystemService(Context.INPUT_METHOD_SERVICE);
            if(imm != null)
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    //endregion
}
