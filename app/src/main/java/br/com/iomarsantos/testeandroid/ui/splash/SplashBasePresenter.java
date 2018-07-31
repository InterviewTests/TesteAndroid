package br.com.iomarsantos.testeandroid.ui.splash;

import br.com.iomarsantos.testeandroid.di.PerActivity;
import br.com.iomarsantos.testeandroid.ui.base.Presenter;

@PerActivity
public interface SplashBasePresenter<V extends SplashView> extends Presenter<V> {

}
