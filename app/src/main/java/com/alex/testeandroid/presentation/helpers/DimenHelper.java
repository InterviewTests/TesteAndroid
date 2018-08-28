package com.alex.testeandroid.presentation.helpers;

import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Created by Alex on 28/08/18.
 */
public class DimenHelper {
    public int toPx(Resources res, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, res.getDisplayMetrics());
    }
}
