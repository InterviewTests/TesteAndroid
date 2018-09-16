package com.santander.luizlago.testeandroid.enums

enum class TypeField(val value: String) {
    TEXT("1"),
    TELNUMBER("telnumber"),
    EMAIL("3");

    companion object {
        fun valueOf(value: String): TypeField? = TypeField.values().find { it.value == value }
    }
}

/**
 * Returns an enum entry with the specified name or `null` if no such entry was found.
 */
inline fun TypeFieldValueOfOrNull(name: String): com.santander.luizlago.testeandroid.enums.TypeField? {
    return com.santander.luizlago.testeandroid.enums.TypeField.values().find { it.value == name }
}