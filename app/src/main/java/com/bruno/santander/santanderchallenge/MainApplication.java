package com.bruno.santander.santanderchallenge;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class MainApplication extends Application {

    @Override
    public void onCreate(){
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/DINNeuzeitGroteskStd-Light.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

}
