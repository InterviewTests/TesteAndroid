package br.com.iomarsantos.testeandroid.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import br.com.iomarsantos.testeandroid.TesteAndroidApp;
import br.com.iomarsantos.testeandroid.di.component.ActivityComponent;
import br.com.iomarsantos.testeandroid.di.component.DaggerActivityComponent;
import br.com.iomarsantos.testeandroid.di.module.ActivityModule;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    private ActivityComponent mActivityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((TesteAndroidApp) getApplication()).getComponent())
                .build();
    }

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }

    protected abstract void setup();

}
