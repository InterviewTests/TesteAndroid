package br.com.iomarsantos.testeandroid;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor;
import com.jacksonandroidnetworking.JacksonParserFactory;

import javax.inject.Inject;

import br.com.iomarsantos.testeandroid.di.component.ApplicationComponent;
import br.com.iomarsantos.testeandroid.di.component.DaggerApplicationComponent;
import br.com.iomarsantos.testeandroid.di.module.ApplicationModule;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class TesteAndroidApp extends Application {

    private ApplicationComponent mApplicationComponent;

    @Inject
    CalligraphyConfig mCalligraphyConfig;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        mApplicationComponent.inject(this);

        AndroidNetworking.initialize(getApplicationContext());

        AndroidNetworking.setParserFactory(new JacksonParserFactory());

        if (BuildConfig.DEBUG) {
            AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.BODY);
        }

        CalligraphyConfig.initDefault(mCalligraphyConfig);

    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }

}
