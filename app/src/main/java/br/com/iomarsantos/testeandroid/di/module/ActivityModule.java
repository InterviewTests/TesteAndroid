package br.com.iomarsantos.testeandroid.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import br.com.iomarsantos.testeandroid.di.ActivityContext;
import br.com.iomarsantos.testeandroid.di.PerActivity;
import br.com.iomarsantos.testeandroid.ui.base.rx.SchedulerProvider;
import br.com.iomarsantos.testeandroid.ui.fundo.FundoBasePresenter;
import br.com.iomarsantos.testeandroid.ui.fundo.FundoPagerAdapter;
import br.com.iomarsantos.testeandroid.ui.fundo.FundoPresenter;
import br.com.iomarsantos.testeandroid.ui.fundo.FundoView;
import br.com.iomarsantos.testeandroid.ui.fundo.contato.ContatoBasePresenter;
import br.com.iomarsantos.testeandroid.ui.fundo.contato.ContatoPresenter;
import br.com.iomarsantos.testeandroid.ui.fundo.contato.ContatoView;
import br.com.iomarsantos.testeandroid.ui.fundo.investimentos.InvestimentoBasePresenter;
import br.com.iomarsantos.testeandroid.ui.fundo.investimentos.InvestimentoPresenter;
import br.com.iomarsantos.testeandroid.ui.fundo.investimentos.InvestimentoView;
import br.com.iomarsantos.testeandroid.ui.splash.SplashBasePresenter;
import br.com.iomarsantos.testeandroid.ui.splash.SplashPresenter;
import br.com.iomarsantos.testeandroid.ui.splash.SplashView;
import br.com.iomarsantos.testeandroid.ui.base.rx.AppSchedulerProvider;
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
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
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

    @Provides
    InvestimentoBasePresenter<InvestimentoView> provideInvestimentoPresenter(
            InvestimentoPresenter<InvestimentoView> presenter) {
        return presenter;
    }

    @Provides
    FundoPagerAdapter provideFundoPagerAdapter(AppCompatActivity activity) {
        return new FundoPagerAdapter(activity.getSupportFragmentManager());
    }

    @Provides
    ContatoBasePresenter<ContatoView> provideContatoPresenter(
            ContatoPresenter<ContatoView> presenter) {
        return presenter;
    }

}
