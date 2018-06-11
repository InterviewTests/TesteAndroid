package com.adenilson.testeandroid.util;

import android.text.TextUtils;
import android.util.Patterns;

import java.util.Locale;

/**
 * @author: Adenilson N. da Silva Junior
 * On date: 10/06/2018
 */

public class StringUtils {

    public static String getFormattedPercentage(double value) {
        return String.format(Locale.getDefault(), "%.2f", value) + "%";
    }

    public static boolean isValidEmail(String email){
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
}
