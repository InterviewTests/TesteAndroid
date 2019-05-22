
package br.banco.services.app.utils;

import java.util.regex.Pattern;


public class ValidatorURL {//

    private boolean mIsValid = false;

    public static final Pattern URL_PATTERN = Pattern.compile(

            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +

                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );

    public boolean isValid() {
        return mIsValid;
    }


    public static boolean isValidURL(CharSequence urlChek) {
        return urlChek != null && URL_PATTERN.matcher(urlChek).matches();
    }

}
