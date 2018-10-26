package com.fuinha11.test.testeandroid.util.extension

import android.support.v4.util.PatternsCompat
import java.util.regex.Pattern

fun String.isValidEmail(): Boolean{
    val phonePattern = Pattern.compile(("[\\w\\d]+@[\\w\\d]+\\.[\\w\\d]+"))
    return !this.isNullOrEmpty() && phonePattern.matcher(this).matches()
}

fun String.isValidText(): Boolean = !this.isNullOrEmpty()

fun String.isValidPhoneNumber(): Boolean {
    val phonePattern = Pattern.compile(("(\\+[0-9]+[\\- \\.]*)?"
            + "(\\([0-9]+\\)[\\- \\.]*)?"
            + "([0-9][0-9\\- \\.]+[0-9])"))

    return !this.isNullOrEmpty() && phonePattern.matcher(this).matches()
}