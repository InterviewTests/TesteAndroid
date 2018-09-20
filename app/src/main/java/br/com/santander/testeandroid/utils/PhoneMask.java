package br.com.santander.testeandroid.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class PhoneMask {

    private static final String mask10 = "(##) ####-####";
    private static final String mask11 = "(##) #####-####";

    public static String unmask(String s) {
        return s.replaceAll("[^0-9]*", "");
    }

    public static TextWatcher insert(final EditText editText) {
        return new TextWatcher() {
            boolean isUpdating;
            String old = "";

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = PhoneMask.unmask(s.toString());
                String mask;
                String defaultMask = getDefaultMask(str);
                switch (str.length()) {
                    case 11:
                        mask = mask11;
                        break;
                    case 10:
                        mask = mask10;
                        break;
                    default:
                        mask = defaultMask;
                        break;
                }

                String tmpMask = "";
                if (isUpdating) {
                    old = str;
                    isUpdating = false;
                    return;
                }
                int i = 0;
                for (char m : mask.toCharArray()) {
                    if ((m != '#' && str.length() > old.length()) || (m != '#' && str.length() < old.length() && str.length() != i)) {
                        tmpMask += m;
                        continue;
                    }

                    try {
                        tmpMask += str.charAt(i);
                    } catch (Exception e) {
                        break;
                    }
                    i++;
                }
                isUpdating = true;
                editText.setText(tmpMask);
                editText.setSelection(tmpMask.length());
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            public void afterTextChanged(Editable s) {
            }
        };
    }

    private static String getDefaultMask(String str) {
        String defaultMask = mask10;
        if (str.length() > 11){
            defaultMask = mask11;
        }
        return defaultMask;
    }

}
