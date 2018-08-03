package br.com.iomarsantos.testeandroid.utils;

import android.content.res.Resources;
import android.util.Log;

public final class ViewUtils {

    private ViewUtils() {}

    public static int dpToPx(float dp) {
        float density = Resources.getSystem().getDisplayMetrics().density;
        int value = Math.round(dp * density);
        return value;
    }

}