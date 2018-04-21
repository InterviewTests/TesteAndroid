package com.bruno.santander.santanderchallenge.contato.presentation;

public class ContatoPresenter implements ContatoContract.Presenter {

    private ContatoContract.View view;

    public ContatoPresenter(ContatoContract.View view){
        this.view = view;
    }

    @Override
    public void getCells() {
//        TODO Obtain the data from JSON (cells.json)
    }

    @Override
    public void saveContato() {
//        TODO Fake saving using RxJava
    }
}
