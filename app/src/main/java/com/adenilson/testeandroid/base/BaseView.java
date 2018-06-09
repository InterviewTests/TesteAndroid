package com.adenilson.testeandroid.base;

/**
 * @author: Adenilson N. da Silva Junior
 * On date: 09/06/2018
 */

public interface BaseView {

    void showLoading();

    void hideLoading();

    void showError(int messageResourceId);
}
