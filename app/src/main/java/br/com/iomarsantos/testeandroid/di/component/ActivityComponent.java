package br.com.iomarsantos.testeandroid.di.component;

import br.com.iomarsantos.testeandroid.di.PerActivity;
import br.com.iomarsantos.testeandroid.di.module.ActivityModule;
import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

}
