package com.seletiva.santander.investment.UI.View;

import android.widget.EditText;

public interface FormFieldListener {
    /**
     * This method shall be called after the text changes. Than, a new tint color will be requested.
     * @param color A new color
     */
    void updateEditTextColor(int color);
}
