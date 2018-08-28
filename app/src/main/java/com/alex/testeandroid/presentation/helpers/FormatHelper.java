package com.alex.testeandroid.presentation.helpers;

/**
 * Created by Alex on 28/08/18.
 */
public class FormatHelper {

    public String formatPercent(float value) {
        return Float.toString(value).replace(".", ",") + "%";
    }
}
