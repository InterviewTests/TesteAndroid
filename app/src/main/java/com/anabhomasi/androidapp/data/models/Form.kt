package com.anabhomasi.androidapp.data.models

object Form {

    data class Response(
        val cells: List<Cell>
    )

    data class Cell(
        val id: Int,
        val type: Int,
        val message: String,
        val typefield: Any,
        val hidden: Boolean,
        val topSpacing: Double,
        val show: Any,
        val required: Boolean
    )

    enum class Type(val code: Int) {
        FIELD (1),
        TEXT (2),
        IMAGE(3),
        CHECKBOX(4),
        SEND(5);

        companion object {
            fun from (value: Int) : Type? = values().find{ it.code == value }
        }
    }
    enum class TypeField (val code: Int){
        TEXT(1),
        TELNUMBER(2),
        EMAIL(3);

        companion object {
            fun from (value: Int) : TypeField? = values().find{ it.code == value }
        }
    }
}