package io.github.pierry.better_call_me;

import android.app.Application;
import android.content.Context;
import com.facebook.stetho.Stetho;
import io.github.pierry.better_call_me.di.Components;
import io.github.pierry.better_call_me.di.DaggerComponents;
import io.github.pierry.better_call_me.di.Modules;
import timber.log.Timber;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class App extends Application {

  private Components components;

  @Override public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    } else {
      //Timber.plant(new Timberize());
    }
    CalligraphyConfig.initDefault(
        new CalligraphyConfig.Builder().setDefaultFontPath("dinpro.otf").setFontAttrId(R.attr.fontPath).build());
    components();
  }

  void components() {
    if (components == null) {
      components = DaggerComponents.builder().modules(new Modules(this)).build();
    }
  }

  public static Components getControllerComponent(Context context) {
    return ((App) context.getApplicationContext()).getComponents();
  }

  public Components getComponents() {
    return components;
  }
}
