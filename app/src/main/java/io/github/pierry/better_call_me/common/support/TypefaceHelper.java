package io.github.pierry.better_call_me.common.support;

import android.content.Context;
import android.graphics.Typeface;

public class TypefaceHelper {

  public Typeface regular(Context context) {
    return Typeface.createFromAsset(context.getAssets(), "dinpro.otf");
  }
}
