package com.bruno.santander.santanderchallenge;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import butterknife.Unbinder;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder mUnbinder;

    public void setUnbinder(Unbinder unbinder){
        mUnbinder = unbinder;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        if(mUnbinder != null){
            mUnbinder.unbind();
        }
    }

    public abstract void setup();

}
