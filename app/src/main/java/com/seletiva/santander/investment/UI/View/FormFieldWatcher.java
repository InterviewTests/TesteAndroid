package com.seletiva.santander.investment.UI.View;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import com.seletiva.santander.investment.R;
import com.seletiva.santander.investment.Utils.StringUtils;

public class FormFieldWatcher implements TextWatcher {
    private FormFieldListener formFieldListener;

    FormFieldWatcher(FormFieldListener listener) {
        formFieldListener = listener;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        boolean isMailValid = StringUtils.validateEmailAdress(charSequence.toString());
        Log.d(getClass().getCanonicalName(), "[#] valid mail? "+ isMailValid);

        if (isMailValid) {
            formFieldListener.updateEditTextColor(R.color.colorPrimary);
        } else {
            formFieldListener.updateEditTextColor(R.color.colorAccent);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {}
}
