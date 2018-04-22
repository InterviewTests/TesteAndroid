package br.com.ibm.santander.wallacebaldenebre.ui.base;

import android.view.View;

public interface MvpView {
    void showProgress();

    void hideProgress();

    void hideKeyboard();

    void showData();
}
