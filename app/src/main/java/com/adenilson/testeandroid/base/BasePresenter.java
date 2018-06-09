package com.adenilson.testeandroid.base;

/**
 * @author: Adenilson N. da Silva Junior
 * On date: 09/06/2018
 */


public interface BasePresenter<V> {

    void attachView(V view);

    void detachView();

}
