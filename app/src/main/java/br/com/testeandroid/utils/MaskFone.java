package br.com.testeandroid.utils;

import android.text.Editable;
import android.text.TextWatcher;

public class MaskFone implements TextWatcher {
    private int length = 0;

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        length = s.length();
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (length < s.length()) {

            if (s.length() == 1) {
                if (Character.isDigit(s.charAt(0))) {
                    s.insert(0, "(");
                }
            } else if (s.length() == 3) {
                s.append(")");
            } else if (s.length() == 8) {
                s.append("-");
            } else if (s.length() > 13) {

                if (Character.isDigit(s.charAt(9))) {
                    s.replace(8, 9, s.charAt(9) + "");
                }

                s.replace(9, 10, "-");

            }
        }
    }
}