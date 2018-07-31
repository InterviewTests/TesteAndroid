package br.com.iomarsantos.testeandroid.ui.base;

import javax.inject.Inject;

/**
 * Classe base que implementa a interface Presenter e fornece uma implementação básica para adicionar ou remover uma referencia a uma view
 */
public class BasePresenter<V extends BaseView> implements Presenter<V> {

    private static final String TAG = "BasePresenter";

    @Inject
    public BasePresenter() {}

}