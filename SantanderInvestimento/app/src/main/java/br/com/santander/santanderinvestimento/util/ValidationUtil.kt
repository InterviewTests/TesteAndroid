package br.com.santander.santanderinvestimento.util

import android.util.Patterns

object ValidationUtil {
    fun isValidEmail(email: String): Boolean = email.isNotEmpty() &&
            Patterns.EMAIL_ADDRESS.matcher(email).matches()

    fun isInvalidName(name: String?): Boolean = name.isNullOrEmpty()

    fun isPhoneInvalid(phone: String): Boolean =
            phone.isNotEmpty() && (phone.length == 10 || phone.length >= 11)
}
