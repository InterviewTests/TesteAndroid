package com.meteoro.testeandroid.core.di.module;

import android.content.Context;

import dagger.Module;

@Module
public class LibraryModule {

    private final Context context;

    public LibraryModule(Context context) {
        this.context = context;
    }
}
