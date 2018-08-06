package br.com.iomarsantos.testeandroid.ui.fundo;

import javax.inject.Inject;

import br.com.iomarsantos.testeandroid.data.Repository;
import br.com.iomarsantos.testeandroid.ui.base.BasePresenter;
import br.com.iomarsantos.testeandroid.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

public class FundoPresenter<V extends FundoView> extends BasePresenter<V>
        implements FundoBasePresenter<V> {

    @Inject
    FundoPresenter(Repository repository,
                   SchedulerProvider schedulerProvider,
                   CompositeDisposable compositeDisposable) {
        super(repository, schedulerProvider, compositeDisposable);
    }

}
