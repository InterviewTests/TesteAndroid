package br.com.iomarsantos.testeandroid.di.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import br.com.iomarsantos.testeandroid.TesteAndroidApp;
import br.com.iomarsantos.testeandroid.data.Repository;
import br.com.iomarsantos.testeandroid.di.ApplicationContext;
import br.com.iomarsantos.testeandroid.di.module.ApplicationModule;
import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(TesteAndroidApp testeAndroidApp);

    @ApplicationContext
    Context context();

    Application application();

    Repository getRepository();

}