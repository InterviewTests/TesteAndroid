package com.meteoro.testeandroid.core.di.component;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.meteoro.testeandroid.core.di.PerActivity;
import com.meteoro.testeandroid.core.di.module.ActivityModule;

import dagger.Component;

@PerActivity
@Component(
        dependencies = ApplicationComponent.class,
        modules = ActivityModule.class
)
public interface ActivityComponent {
    AppCompatActivity activity();

    FragmentManager fragmentManager();
}
