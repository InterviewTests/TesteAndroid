package com.nataliafavero.santander.ui.fields;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.nataliafavero.santander.R;
import com.nataliafavero.santander.ui.utils.Utils;

/**
 * Created by nataliafavero on 13/09/18.
 */

public class CellTextInputLayout extends TextInputLayout {

    private TextInputEditText editText;
    private RelativeLayout.LayoutParams textInputLayoutParams;

    public CellTextInputLayout(Context context) {
        super(new ContextThemeWrapper(context, R.style.EditTextHint));
        textInputLayoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        setLayoutParams(textInputLayoutParams);
        setId(View.generateViewId());
    }


    public void setHint(String text) {
        if (editText == null) {
            editText = new TextInputEditText(getContext());
        }
        editText.setId(View.generateViewId());
        editText.setHint(text);
        LinearLayout.LayoutParams editTextParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        editText.setLayoutParams(editTextParams);
        this.addView(editText, editTextParams);
    }

    public void setInputType(int inputType) {
        editText.setInputType(inputType);
    }

    public void setBelow(Integer id) {
        if (id != null) {
            textInputLayoutParams.addRule(RelativeLayout.BELOW, id);
        }
    }

    public void setMarginTop(int marginTopPx) {
        textInputLayoutParams.setMargins(20,  Utils.convertDpToPixel(getContext(), marginTopPx), 20, 0 );
    }


}
