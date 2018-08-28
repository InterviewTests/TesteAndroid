package com.alex.testeandroid.presentation.helpers;

import android.text.TextUtils;
import android.util.Patterns;

/**
 * Created by Alex on 28/08/18.
 */
public class ValidatorHelper {

    //region METHODS
    //region PUBLIC METHODS
    public boolean isEmpty(String value) {
        return TextUtils.isEmpty(value);
    }

    public boolean isValidEmail(String email) {
        if (isEmpty(email)) return false;
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public boolean isValidPhone(String phone) {
        if (isEmpty(phone)) return false;
        return Patterns.PHONE.matcher(phone).matches();
    }
    //endregion
    //endregion
}
