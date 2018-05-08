package rrzaniolo.testandroidsantander.base.custom;

/*
 * Created by rrzaniolo on 08/05/18.
 * Copyright © 2018 rrzaniolo. All rights reserved.
 */

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Field;

public class CustomTextInputLayout extends TextInputLayout {
    public CustomTextInputLayout(Context context) {
        super(context);
    }

    public CustomTextInputLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomTextInputLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void handleErrorAppearance() {
        try {
            setError(" ");
            Field fErrorView = getClass().getSuperclass().getDeclaredField("mErrorView");
            fErrorView.setAccessible(true);

            TextView tvErrorView = (TextView) fErrorView.get(this);
            ((LinearLayout) tvErrorView.getParent()).setVisibility(View.GONE);

            invalidate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
