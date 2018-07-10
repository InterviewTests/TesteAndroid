package com.meteoro.testeandroid.core.di.module;

import android.content.Context;

import com.meteoro.testeandroid.core.di.qualifers.IoScheduler;
import com.meteoro.testeandroid.core.di.qualifers.UiScheduler;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@Module
public class ApplicationModule {

    private final Context applicationContext;

    public ApplicationModule(Context applicationContext) {
        this.applicationContext = applicationContext.getApplicationContext();
    }

    @Provides
    @Singleton
    Context providesApplicationModule() {
        return applicationContext;
    }

    @Provides
    @Singleton
    @UiScheduler
    Scheduler providesMainThreadScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Singleton
    @IoScheduler
    Scheduler providesJobScheduler() {
        return Schedulers.io();
    }
}
