package com.vctapps.santanderchallenge.core.domain

object StringPatterns {

    val EMAIL_REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}\$"

    val PHONE_NUMBER_REGEX = "\\(\\d{2}\\)\\s\\d{4}-\\d{4}|\\(\\d{2}\\)\\s\\d{5}-\\d{4}"

}