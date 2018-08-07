package resource.com.br.santanderapp;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/DINPro-Medium.otf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }
}
