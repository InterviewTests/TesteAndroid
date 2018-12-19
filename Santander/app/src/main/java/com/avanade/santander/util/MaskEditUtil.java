package com.avanade.santander.util;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public abstract class MaskEditUtil {

    private static final String mask10 = "(##) ####-####";
    private static final String mask11 = "(##) #####-####";
    private static final String mask8 = "####-####";
    private static final String mask9 = "#####-####";

    public static String unmask(String s) {
        return s.replaceAll("[^0-9]*", "");
    }

    public static TextWatcher insert(final EditText editText) {
        return new TextWatcher() {
            boolean isUpdating;
            String old = "";

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = MaskEditUtil.unmask(s.toString());
                String mask;
                String defaultMask = getDefaultMask(str);
                switch (str.length()) {
                    case 11:
                        editText.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
                        mask = mask11;
                        break;
                    case 10:
                        editText.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
                        mask = mask10;
                        break;
                    case 9:
                        editText.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
                        mask = mask9;
                        break;
                    default:
                        editText.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
                        mask = defaultMask;
                        break;
                }

                String mascara = "";
                if (isUpdating) {
                    old = str;
                    isUpdating = false;
                    return;
                }
                int i = 0;
                for (char m : mask.toCharArray()) {
                    if ((m != '#' && str.length() > old.length()) || (m != '#' && str.length() < old.length() && str.length() != i)) {
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
                editText.setText(mascara);
                editText.setSelection(mascara.length());
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            public void afterTextChanged(Editable s) {
            }
        };
    }

    private static String getDefaultMask(String str) {
        String defaultMask = mask8;
        if (str.length() > 11){
            defaultMask = mask11;
        }
        return defaultMask;
    }
}