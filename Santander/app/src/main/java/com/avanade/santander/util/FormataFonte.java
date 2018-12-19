package com.avanade.santander.util;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;

import com.avanade.santander.R;

public class FormataFonte {

    public static Typeface formataProMedium(Context context) {
        return ResourcesCompat.getFont(context, R.font.din_pro_medium);
    }

    public static Typeface formataProRegular(Context context) {
        return ResourcesCompat.getFont(context, R.font.din_pro_regular);
    }
}