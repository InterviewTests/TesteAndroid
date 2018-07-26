package com.rafhack.testeandroid.data.entities

import com.rafhack.testeandroid.R

enum class FieldType(var typeId: Int?, var errorMessage: Int) {

    TEXT(1, R.string.text_field_error),
    PHONE_NUMBER(2, R.string.tel_number_field_error),
    EMAIL_ADDRESS(3, R.string.email_field_error),
    NONE(null, 0);

    companion object {
        fun from(findValue: Int): FieldType = FieldType.values().first { it.typeId == findValue }
    }

}