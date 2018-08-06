package br.com.iomarsantos.testeandroid.ui.splash;

import javax.inject.Inject;

import br.com.iomarsantos.testeandroid.data.Repository;
import br.com.iomarsantos.testeandroid.ui.base.BasePresenter;
import br.com.iomarsantos.testeandroid.utils.rx.SchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;

public class SplashPresenter<V extends SplashView> extends BasePresenter<V>
        implements SplashBasePresenter<V> {

    @Inject
    SplashPresenter(Repository repository,
                    SchedulerProvider schedulerProvider,
                    CompositeDisposable compositeDisposable) {
        super(repository, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onAttach(V view) {
        super.onAttach(view);
        getView().openFundoActivity();
    }


}
