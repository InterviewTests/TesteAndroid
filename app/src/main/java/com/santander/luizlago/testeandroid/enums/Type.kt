package com.santander.luizlago.testeandroid.enums

enum class Type(val value: Int) {
    FIELD(1),
    TEXT(2),
    IMAGE(3),
    CHECKBOX(4),
    SEND(5);

    companion object {
        fun valueOf(value: Int): Type? = Type.values().find { it.value == value }
    }
}