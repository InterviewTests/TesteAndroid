package br.com.iomarsantos.testeandroid.ui.fundo.contato;

import javax.inject.Inject;

import br.com.iomarsantos.testeandroid.ui.base.BasePresenter;
import io.reactivex.disposables.CompositeDisposable;

public class ContatoPresenter<V extends ContatoView> extends BasePresenter<V>
        implements ContatoBasePresenter<V> {

    @Inject
    ContatoPresenter(CompositeDisposable compositeDisposable) {
        super(compositeDisposable);
    }

}
