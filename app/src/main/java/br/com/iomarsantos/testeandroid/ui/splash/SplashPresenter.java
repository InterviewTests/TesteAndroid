package br.com.iomarsantos.testeandroid.ui.splash;

import javax.inject.Inject;

import br.com.iomarsantos.testeandroid.ui.base.BasePresenter;
import io.reactivex.disposables.CompositeDisposable;

public class SplashPresenter<V extends SplashView> extends BasePresenter<V>
        implements SplashBasePresenter<V> {

    @Inject
    SplashPresenter(CompositeDisposable compositeDisposable) {
        super(compositeDisposable);
    }

}
