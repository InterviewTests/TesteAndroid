package com.bruno.santander.santanderchallenge.contato.presentation;

import com.bruno.santander.santanderchallenge.presentation.BaseView;

public interface ContatoContract {

    interface View extends BaseView<ContatoPresenter>{
        void onSuccessGetCells();
        void onSuccessSaveContato();
    }

    interface Presenter{
        void getCells();
        void saveContato();
    }

}
