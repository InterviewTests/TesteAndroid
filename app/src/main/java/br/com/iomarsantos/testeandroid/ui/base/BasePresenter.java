package br.com.iomarsantos.testeandroid.ui.base;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Classe base que implementa a interface Presenter e fornece uma implementação básica para adicionar ou remover uma referencia a uma view
 */
public class BasePresenter<V extends BaseView> implements Presenter<V> {

    private static final String TAG = "BasePresenter";

    private final CompositeDisposable mCompositeDisposable;

    private V mBaseView;

    @Inject
    public BasePresenter(CompositeDisposable mCompositeDisposable) {
        this.mCompositeDisposable = mCompositeDisposable;
    }

    @Override
    public void onAttach(V baseView) {
        this.mBaseView = baseView;
    }

    @Override
    public void onDetach() {
        this.mCompositeDisposable.dispose();
        this.mBaseView = null;
    }

    public V getView() {
        return this.mBaseView;
    }

    public boolean isViewAttached() {
        return this.mBaseView != null;
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }


}