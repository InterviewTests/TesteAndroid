package br.com.iomarsantos.testeandroid.ui.fundo.investimentos;

import javax.inject.Inject;

import br.com.iomarsantos.testeandroid.data.Repository;
import br.com.iomarsantos.testeandroid.ui.base.BasePresenter;
import br.com.iomarsantos.testeandroid.ui.base.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

public class InvestimentoPresenter<V extends InvestimentoView> extends BasePresenter<V>
        implements InvestimentoBasePresenter<V> {

    @Inject
    InvestimentoPresenter(Repository repository,
                          SchedulerProvider schedulerProvider,
                          CompositeDisposable compositeDisposable) {
        super(repository, schedulerProvider, compositeDisposable);
    }

}
