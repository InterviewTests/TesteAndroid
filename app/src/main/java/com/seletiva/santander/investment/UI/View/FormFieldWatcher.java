package com.seletiva.santander.investment.UI.View;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.seletiva.santander.investment.R;
import com.seletiva.santander.investment.Utils.StringUtils;

import java.lang.ref.WeakReference;

public class FormFieldWatcher implements TextWatcher {
    //fixme: add as a weak reference
    private EditText attachedEditText;
    private FormFieldListener formFieldListener;

    public FormFieldWatcher(EditText attachedEditText, FormFieldListener listener) {
        this.attachedEditText = attachedEditText;
        formFieldListener = listener;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        boolean isMailValid = StringUtils.validateEmailAdress(charSequence.toString());
        Log.d(getClass().getCanonicalName(), "[#] valid mail? "+ isMailValid);

        if (isMailValid) {
            formFieldListener.updateEditTextColor(attachedEditText, R.color.colorPrimary);
        } else {
            formFieldListener.updateEditTextColor(attachedEditText, R.color.colorAccent);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
