package com.rafhack.testeandroid.data.entities

enum class Type(var typeId: Int) {
    FIELD(1),
    TEXT(2),
    IMAGE(3),
    CHECKBOX(4),
    SEND(5);

    companion object {
        fun from(findValue: Int): Type = Type.values().first { it.typeId == findValue }
    }

}