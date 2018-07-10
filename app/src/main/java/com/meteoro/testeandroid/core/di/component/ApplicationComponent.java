package com.meteoro.testeandroid.core.di.component;

import android.content.Context;

import com.meteoro.testeandroid.core.di.module.ApplicationModule;
import com.meteoro.testeandroid.core.di.qualifers.IoScheduler;
import com.meteoro.testeandroid.core.di.qualifers.UiScheduler;
import com.meteoro.testeandroid.core.view.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;
import rx.Scheduler;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    void inject(BaseActivity activity);

    Context context();

    @UiScheduler
    Scheduler mainThreadScheduler();

    @IoScheduler
    Scheduler jobScheduler();
}
