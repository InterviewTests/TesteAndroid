package com.bruno.santander.santanderchallenge.presentation;

public interface BaseView<T> {

    void setPresenter(T presenter);

    void onError(String msg);

}
