package br.com.iomarsantos.testeandroid.utils;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public final class ViewUtils {

    private ViewUtils() {
    }

    public static int dpToPx(float dp) {
        float density = Resources.getSystem().getDisplayMetrics().density;
        int value = Math.round(dp * density);
        return value;
    }

    public static void centerToolbarTitle(@NonNull final Toolbar toolbar) {
        final CharSequence title = toolbar.getTitle();
        final ArrayList<View> views = new ArrayList<>(1);
        toolbar.findViewsWithText(views, title, View.FIND_VIEWS_WITH_TEXT);
        if (!views.isEmpty()) {
            final TextView titleView = (TextView) views.get(0);
            titleView.setGravity(Gravity.CENTER);
            final Toolbar.LayoutParams layoutParams = (Toolbar.LayoutParams) titleView.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            toolbar.requestLayout();
        }
    }

}