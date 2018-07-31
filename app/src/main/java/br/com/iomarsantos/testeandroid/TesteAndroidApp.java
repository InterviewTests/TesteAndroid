package br.com.iomarsantos.testeandroid;

import android.app.Application;

import br.com.iomarsantos.testeandroid.di.component.ApplicationComponent;
import br.com.iomarsantos.testeandroid.di.component.DaggerApplicationComponent;
import br.com.iomarsantos.testeandroid.di.module.ApplicationModule;

public class TesteAndroidApp extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        mApplicationComponent.inject(this);

    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }

}
