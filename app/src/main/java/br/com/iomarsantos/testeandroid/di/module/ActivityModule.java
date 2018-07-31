package br.com.iomarsantos.testeandroid.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import br.com.iomarsantos.testeandroid.di.ActivityContext;
import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private AppCompatActivity activity;

    public ActivityModule(AppCompatActivity appCompatActivity){
        this.activity = appCompatActivity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return activity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return activity;
    }

}
