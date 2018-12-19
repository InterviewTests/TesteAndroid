package com.seletiva.santander.investment.UI.View;

import android.widget.EditText;

public interface FormFieldListener {
    /**
     * This method shall be called after the text changes. Than, a new tint color will be requested.
     *
     * @param targetEditText A valid editText attached to the watcher
     * @param color A new color
     */
    void updateEditTextColor(EditText targetEditText, int color);
}
