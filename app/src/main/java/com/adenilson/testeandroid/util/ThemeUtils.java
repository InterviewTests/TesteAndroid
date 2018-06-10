package com.adenilson.testeandroid.util;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;

/**
 * @author: Adenilson N. da Silva Junior
 * On date: 10/06/2018
 */

public class ThemeUtils {

    public static void changeColorProgressSwipeRefresh(SwipeRefreshLayout swipeRefreshLayout) {
        //BaseColor
        int color = Color.parseColor("#EA0404");
        swipeRefreshLayout.setColorSchemeColors(color);
    }
}
