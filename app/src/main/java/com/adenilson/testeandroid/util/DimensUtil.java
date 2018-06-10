package com.adenilson.testeandroid.util;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * @author: Adenilson N. da Silva Junior
 * On date: 10/06/2018
 */

public class DimensUtil {

    public static int dpToPx(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

}
