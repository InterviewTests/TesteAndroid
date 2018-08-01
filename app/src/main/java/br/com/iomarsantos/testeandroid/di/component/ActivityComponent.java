package br.com.iomarsantos.testeandroid.di.component;

import br.com.iomarsantos.testeandroid.di.PerActivity;
import br.com.iomarsantos.testeandroid.di.module.ActivityModule;
import br.com.iomarsantos.testeandroid.ui.fundo.FundoActivity;
import br.com.iomarsantos.testeandroid.ui.fundo.contato.ContatoFragment;
import br.com.iomarsantos.testeandroid.ui.fundo.investimentos.InvestimentoFragment;
import br.com.iomarsantos.testeandroid.ui.splash.SplashActivity;
import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(SplashActivity splashActivity);
    void inject(FundoActivity fundoActivity);
    void inject(InvestimentoFragment investimentoFragment);
    void inject(ContatoFragment contatoFragment);
}
