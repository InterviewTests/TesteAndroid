package com.study.vipoliveira.investapp.util

import android.support.v4.util.PatternsCompat
import java.util.regex.Pattern

fun String.isValidEmail(): Boolean = !this.isNullOrEmpty()
        && PatternsCompat.EMAIL_ADDRESS.matcher(this).matches()

fun String.isValidText(): Boolean = !this.isNullOrEmpty()

fun String.isValidPhoneNumber(): Boolean{
    val phonePattern = Pattern.compile(("(\\+[0-9]+[\\- \\.]*)?"
            + "(\\([0-9]+\\)[\\- \\.]*)?"
            + "([0-9][0-9\\- \\.]+[0-9])"))

    return !this.isNullOrEmpty() && phonePattern.matcher(this).matches()
}