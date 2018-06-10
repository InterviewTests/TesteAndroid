package com.adenilson.testeandroid.base;

import android.support.v4.app.Fragment;

/**
 * @author: Adenilson N. da Silva Junior
 * On date: 09/06/2018
 */

public abstract class BaseFragment extends Fragment implements BaseView {

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(int messageResourceId) {

    }
}
