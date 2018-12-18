package com.avanade.santander.util;

import android.content.Context;
import android.util.TypedValue;

public class DpToPixels {

    public static int convertToPixels(int dp, Context context){
       return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }
}
