package br.com.iomarsantos.testeandroid.ui.fundo;

import javax.inject.Inject;

import br.com.iomarsantos.testeandroid.ui.base.BasePresenter;
import io.reactivex.disposables.CompositeDisposable;

public class FundoPresenter<V extends FundoView> extends BasePresenter<V>
        implements FundoBasePresenter<V> {

    @Inject
    FundoPresenter(CompositeDisposable compositeDisposable) {
        super(compositeDisposable);
    }

}
