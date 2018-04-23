package com.bruno.santander.santanderchallenge.contato.presentation;

import android.support.design.widget.CoordinatorLayout;

import com.bruno.santander.santanderchallenge.IEditTextContato;
import com.bruno.santander.santanderchallenge.contato.model.ListCell;
import com.bruno.santander.santanderchallenge.presentation.BasePresenter;
import com.bruno.santander.santanderchallenge.presentation.BaseView;

public interface ContatoContract {

    interface View extends BaseView<ContatoPresenter>{
        void onSuccessGetCells(ListCell cells);
        void onSuccessSaveContato();
    }

    interface Presenter extends BasePresenter {
        void getCells();
        void setEditTextContato(IEditTextContato editTextContato);
        void setScreenData(CoordinatorLayout coordinatorLayout, ListCell cells);
        void saveContato();
    }

}
