package br.com.iomarsantos.testeandroid.di.component;

import javax.inject.Singleton;

import br.com.iomarsantos.testeandroid.di.module.ApplicationTestModule;
import dagger.Component;

@Singleton
@Component(modules = ApplicationTestModule.class)
public interface TestComponent extends ApplicationComponent {
}
