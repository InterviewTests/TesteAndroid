package br.com.iomarsantos.testeandroid.ui.base;

import com.androidnetworking.error.ANError;

/**
 * Cada presenter deve implementar essa interface ou estender o BasePresenter
 * indicando o tipo BaseView que deseja ser anexado.
 */
public interface Presenter<V extends BaseView> {
    void onAttach(V baseView);
    void onDetach();
    void handleApiError(ANError error);
}

