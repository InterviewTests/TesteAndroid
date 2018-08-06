package br.com.iomarsantos.testeandroid.di.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import br.com.iomarsantos.testeandroid.R;
import br.com.iomarsantos.testeandroid.data.AppRepository;
import br.com.iomarsantos.testeandroid.data.Repository;
import br.com.iomarsantos.testeandroid.data.network.Api;
import br.com.iomarsantos.testeandroid.data.network.AppApi;
import br.com.iomarsantos.testeandroid.di.ApplicationContext;
import dagger.Module;
import dagger.Provides;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

@Module
public class ApplicationTestModule {

    private final Application mApplication;

    public ApplicationTestModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    Repository provideaRepository(AppRepository appRepository) {
        return appRepository;
    }

    @Provides
    @Singleton
    Api provideApi(AppApi appApi) {
        return appApi;
    }

    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/source-sans-pro/SourceSansPro-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }
}

