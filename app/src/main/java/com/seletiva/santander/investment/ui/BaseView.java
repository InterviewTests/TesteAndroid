package com.seletiva.santander.investment.ui;

public interface BaseView<T extends BasePresenter> {
    void setPresenter(T presenter);
}