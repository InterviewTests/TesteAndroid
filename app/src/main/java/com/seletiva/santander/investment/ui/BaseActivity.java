package com.seletiva.santander.investment.ui;

import android.support.v7.app.AppCompatActivity;

import org.androidannotations.annotations.EActivity;
import org.greenrobot.eventbus.EventBus;

@EActivity
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);
    }
}
