package br.com.iomarsantos.testeandroid.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import br.com.iomarsantos.testeandroid.di.ActivityContext;
import br.com.iomarsantos.testeandroid.di.PerActivity;
import br.com.iomarsantos.testeandroid.ui.fundo.FundoBasePresenter;
import br.com.iomarsantos.testeandroid.ui.fundo.FundoPresenter;
import br.com.iomarsantos.testeandroid.ui.fundo.FundoView;
import br.com.iomarsantos.testeandroid.ui.splash.SplashBasePresenter;
import br.com.iomarsantos.testeandroid.ui.splash.SplashPresenter;
import br.com.iomarsantos.testeandroid.ui.splash.SplashView;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ActivityModule {

    private AppCompatActivity activity;

    public ActivityModule(AppCompatActivity appCompatActivity) {
        this.activity = appCompatActivity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return activity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return activity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @PerActivity
    SplashBasePresenter<SplashView> provideSplashPresenter(
            SplashPresenter<SplashView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    FundoBasePresenter<FundoView> provideFundoPresenter(
            FundoPresenter<FundoView> presenter) {
        return presenter;
    }

}
