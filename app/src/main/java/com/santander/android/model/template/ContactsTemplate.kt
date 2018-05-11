package com.santander.android.model.template

import java.io.Serializable

data class ContactsTemplate(
        var cells: List<ContactTemplate> = ArrayList()
): Serializable {

    data class ContactTemplate(
            var id: Int? = null,
            var type: Int? = null,
            var message: String? = null,
            var typefield: String? = null,
            var hidden: Boolean = false,
            var topSpacing: Int? = null,
            var show: Int? = null,
            var required: Boolean = false
    ): Serializable

    enum class Type(val value: Int) {

        Field(1),
        Text(2),
        Image(3),
        Checkbox(4),
        Send(5);

        companion object {
            fun from(findValue: Int?): Type = Type.values().first { it.value == findValue }
        }

    }

    enum class TypeField(val value: Int) {

        Text(1),
        TelNumber(2),
        Email(3);

        companion object {
            // Fixes Broken "telnumber" type field.
            fun from(findValue: String?): TypeField = TypeField.values().first { it.value == fixBrokenJson(findValue) }
            private fun fixBrokenJson(brokenValue: String?): Int? {
                return if (brokenValue == "telnumber") 2 else brokenValue?.toFloat()?.toInt()
            }
        }

    }

}