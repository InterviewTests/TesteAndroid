package com.nataliafavero.santander.ui.fields;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.nataliafavero.santander.R;
import com.nataliafavero.santander.ui.utils.PhoneNumberFormat;
import com.nataliafavero.santander.ui.utils.Utils;

import java.lang.ref.WeakReference;

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
    }


    public void setHint(String text) {
        createEditText();
        editText.setHint(text);
        LinearLayout.LayoutParams editTextParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        editText.setLayoutParams(editTextParams);
        this.addView(editText, editTextParams);
    }

    public void setInputType(int inputType) {
        switch (inputType){
            case InputType.TYPE_CLASS_PHONE:
                PhoneNumberFormat watcher = new PhoneNumberFormat(new WeakReference<EditText>(editText));
                editText.addTextChangedListener(watcher);
                break;
        }

        editText.setInputType(inputType);
    }

    public void setBelow(Integer id) {
        if (id != null) {
            textInputLayoutParams.addRule(RelativeLayout.BELOW, id);
        }
    }

    public void setMarginTop(int marginTopPx) {
        int marginInPx = Utils.convertDpToPixel(getContext(), 20);
        textInputLayoutParams.setMargins(marginInPx, Utils.convertDpToPixel(getContext(), marginTopPx), marginInPx, 0);
    }

    public TextInputEditText getEditText() {
        return editText;
    }

    private void createEditText() {
        if (editText == null) {
            editText = new TextInputEditText(getContext());
            editText.setId(View.generateViewId());
        }
        final CellTextInputLayout self = this;
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s)) {

                    self.setError(null);
                    self.setErrorEnabled(false);
                    editText.getBackground().setColorFilter(getResources().getColor(R.color.riskGreen), PorterDuff.Mode.SRC_ATOP);

                    if (editText.getInputType() == InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                            && !Patterns.EMAIL_ADDRESS.matcher(s).matches()) {
                        self.setErrorEnabled(true);
                        self.setError(getContext().getResources().getString(R.string.contact_error_email));
                    }
                } else {
                    self.setErrorEnabled(true);
                    self.setError("");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


}
