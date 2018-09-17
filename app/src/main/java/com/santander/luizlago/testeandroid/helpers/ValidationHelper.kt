package com.santander.luizlago.testeandroid.helpers

import android.text.TextUtils
import android.util.Patterns

class ValidationHelper {

    companion object {
        fun isValidEmail(email: String): Boolean {
            //return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
            return email.matches(("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+").toRegex())
        }

        fun isValidPhoneNumber(number: String) : Boolean {
            val length = MaskHelper().unmask(number).length
            return (length != 11) && (length != 10) && length != 9 && length != 8
        }

        fun isValidText(text: String) : Boolean {
            return text.isNullOrEmpty()
        }
     }

}