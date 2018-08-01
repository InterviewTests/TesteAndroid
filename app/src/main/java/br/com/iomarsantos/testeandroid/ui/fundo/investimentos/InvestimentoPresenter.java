package br.com.iomarsantos.testeandroid.ui.fundo.investimentos;

import javax.inject.Inject;

import br.com.iomarsantos.testeandroid.ui.base.BasePresenter;
import br.com.iomarsantos.testeandroid.ui.fundo.FundoBasePresenter;
import br.com.iomarsantos.testeandroid.ui.fundo.FundoView;
import io.reactivex.disposables.CompositeDisposable;

public class InvestimentoPresenter<V extends InvestimentoView> extends BasePresenter<V>
        implements InvestimentoBasePresenter<V> {

    @Inject
    InvestimentoPresenter(CompositeDisposable compositeDisposable) {
        super(compositeDisposable);
    }

}
