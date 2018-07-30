package com.carpinelli.testeandroid.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class Mask {


    public static TextWatcher insertPhoneMask(final EditText ediTxt) {
        return new TextWatcher() {
            boolean isUpdating;
            String old = "";
            String mask9 = "(##)#####-####";
            String mask8 = "(##)####-####";
            String mask;

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String phone = ediTxt.getText().toString().trim().replaceAll("[^\\d.]", "");

                if (phone.length() < 11) {
                    mask = mask8;
                } else {
                    mask = mask9;
                }

                String str = s.toString().replaceAll("[.]", "").replaceAll("[-]", "")
                        .replaceAll("[/]", "").replaceAll("[(]", "")
                        .replaceAll("[)]", "");

                if (ediTxt.length() > 9) {
                    if (str.substring(0, 1).equals("0")) {
                        str = str.substring(1, str.length());
                    }
                }
                String mascara = "";
                if (isUpdating) {
                    old = str;
                    isUpdating = false;
                    return;
                }
                int i = 0;
                for (char m : mask.toCharArray()) {
                    if (m != '#' && str.length() != old.length()) {
                        mascara += m;
                        continue;
                    }
                    try {
                        mascara += str.charAt(i);
                    } catch (Exception e) {
                        break;
                    }
                    i++;
                }
                isUpdating = true;
                ediTxt.setText(mascara);
                ediTxt.setSelection(mascara.length());
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }
        };
    }

}
