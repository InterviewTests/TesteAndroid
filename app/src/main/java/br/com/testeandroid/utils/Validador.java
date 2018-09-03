package br.com.testeandroid.utils;

import android.graphics.Color;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import java.util.List;
import java.util.regex.Pattern;

import br.com.testeandroid.R;

public class Validador {

    public static boolean validadeForm(List<EditText> views) {
        boolean resp = true;

        for (EditText editText : views) {
            String textToValidade = editText.getText().toString();

            switch (editText.getInputType()) {

                case InputType.TYPE_CLASS_TEXT:
                    if (TextUtils.isEmpty(textToValidade)) {
                        editText.setBackgroundResource(R.drawable.edt_linha);
                        resp = false;
                    }else {
                        editText.setBackgroundResource(R.drawable.edt_linha_validado);
                    }
                    break;
                case InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS:
                    if (editText.getVisibility() == View.VISIBLE) {
                        if (!ValidarEmail(textToValidade)) {
                            editText.setBackgroundResource(R.drawable.edt_linha);
                            resp = false;
                        }else {
                            editText.setBackgroundResource(R.drawable.edt_linha_validado);
                        }
                    }
                    break;
                case InputType.TYPE_CLASS_PHONE:
                    if (!ValidarTelefone(textToValidade)) {
                        editText.setBackgroundResource(R.drawable.edt_linha);
                        resp = false;
                    }else {
                        editText.setBackgroundResource(R.drawable.edt_linha_validado);
                    }
                    break;
            }
        }
        return resp;
    }


    public static boolean ValidarTelefone(String fone) {
        return fone.matches("\\(\\d{2}\\)\\d{4}\\-\\d{4}") ||
                fone.matches("\\(\\d{2}\\)\\d{5}\\-\\d{4}");
    }

    public static boolean ValidarEmail(String email) {
        return email.matches("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+");
    }
}
