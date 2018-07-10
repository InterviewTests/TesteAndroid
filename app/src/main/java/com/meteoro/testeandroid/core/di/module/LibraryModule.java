package com.meteoro.testeandroid.core.di.module;

import android.content.Context;

import com.meteoro.testeandroid.R;
import com.meteoro.testeandroid.core.data.remote.RepositoryApi;
import com.meteoro.testeandroid.core.data.remote.RepositoryApiImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LibraryModule {

    private final Context context;

    public LibraryModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    RepositoryApi providesRepositoryApi() {
        String host = context.getString(R.string.host_api);
        return new RepositoryApiImpl(host);
    }
}
