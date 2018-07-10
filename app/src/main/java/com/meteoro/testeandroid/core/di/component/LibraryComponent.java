package com.meteoro.testeandroid.core.di.component;

import android.content.Context;

import com.meteoro.testeandroid.core.data.remote.RepositoryApi;
import com.meteoro.testeandroid.core.di.module.ApplicationModule;
import com.meteoro.testeandroid.core.di.module.LibraryModule;
import com.meteoro.testeandroid.core.di.qualifers.IoScheduler;
import com.meteoro.testeandroid.core.di.qualifers.UiScheduler;

import javax.inject.Singleton;

import dagger.Component;
import rx.Scheduler;

@Singleton
@Component(modules = {LibraryModule.class, ApplicationModule.class})
public interface LibraryComponent {
    Context context();

    @IoScheduler
    Scheduler ioScheduler();

    @UiScheduler
    Scheduler uiScheduler();

    RepositoryApi repositoryApi();
}
