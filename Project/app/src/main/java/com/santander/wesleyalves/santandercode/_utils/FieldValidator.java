package com.santander.wesleyalves.santandercode._utils;

import android.widget.EditText;

public class FieldValidator {

    public static boolean EditTextValidator(EditText editText, int tamanhoMinimo, String mensagemErro) {
        if (editText.getText().toString().trim().length() >= tamanhoMinimo) {
            editText.setError(null);
            return true;
        }

        editText.setError(mensagemErro);
        return false;
    }

    public static boolean EmailTextValidator(EditText editText, String mensagemErro) {
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(editText.getText().toString()).matches()) {
            editText.setError(null);
            return true;
        }

        editText.setError(mensagemErro);
        return false;
    }

    public static boolean PhoneValidator(EditText editText, String mensagemErro) {
        if (android.util.Patterns.PHONE.matcher(editText.getText().toString()).matches()) {
            editText.setError(null);
            return true;
        }

        editText.setError(mensagemErro);
        return false;
    }
}
