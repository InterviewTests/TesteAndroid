package com.nataliafavero.santander.ui.utils;

import android.content.Context;

/**
 * Created by nataliafavero on 13/09/18.
 */

public final class Utils {

    public static int convertDpToPixel(Context context, int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }
}
