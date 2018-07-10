package com.meteoro.testeandroid.application;

import android.app.Application;
import android.content.Context;

import com.meteoro.testeandroid.core.di.component.DaggerLibraryComponent;
import com.meteoro.testeandroid.core.di.component.LibraryComponent;
import com.meteoro.testeandroid.core.di.module.ApplicationModule;
import com.meteoro.testeandroid.core.di.module.LibraryModule;

public class MyApplication extends Application {

    private LibraryComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        initDagger();
    }

    private void initDagger() {
        component = DaggerLibraryComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .libraryModule(new LibraryModule(this))
                .build();
    }

    public LibraryComponent getComponent() {
        return component;
    }

    public static MyApplication get(Context context) {
        return (MyApplication) context.getApplicationContext();
    }
}
