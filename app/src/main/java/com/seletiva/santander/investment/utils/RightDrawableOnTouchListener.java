package com.seletiva.santander.investment.utils;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public abstract class RightDrawableOnTouchListener implements View.OnTouchListener {
    private Drawable drawable;

    protected RightDrawableOnTouchListener(TextView view) {
        super();

        final Drawable[] drawables = view.getCompoundDrawables();
        final int lengthToContainDrawable = 4;
        final int drawableIndex = 2;

        if (drawables.length == lengthToContainDrawable)
            this.drawable = drawables[drawableIndex];
    }

    @Override
    public boolean onTouch(final View v, final MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN && drawable != null) {
            final int padding = 10;
            final int x = (int) event.getX();
            final int y = (int) event.getY();
            final Rect bounds = drawable.getBounds();

            if (x >= (v.getRight() - bounds.width() - padding) &&
                    x <= (v.getRight() - v.getPaddingRight() + padding) &&
                    y >= (v.getPaddingTop() - padding) &&
                    y <= (v.getHeight() - v.getPaddingBottom()) + padding) {
                return onDrawableTouch(event);
            }
        }

        return false;
    }

    public abstract boolean onDrawableTouch(final MotionEvent event);
}

