package com.meteoro.testeandroid.core.di.module;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.meteoro.testeandroid.core.di.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private final AppCompatActivity activity;

    public ActivityModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    AppCompatActivity activity() {
        return activity;
    }

    @Provides
    @PerActivity
    FragmentManager fragmentManager() {
        return activity.getSupportFragmentManager();
    }
}
