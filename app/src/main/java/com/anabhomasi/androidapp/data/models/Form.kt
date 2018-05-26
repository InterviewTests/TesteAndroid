package com.anabhomasi.androidapp.data.models

object Form {

    data class Response(
        val cells: List<Cell>
    )

    data class Cell(
        val id: Int,
        val type: Int,
        val message: String,
        val typefield: String?,
        val hidden: Boolean,
        val topSpacing: Double,
        val show: Int?,
        val required: Boolean
    )


    const val TYPE_FIELD = 1
    const val TYPE_TEXT = 2
    const val TYPE_IMAGE = 3
    const val TYPE_CHECKBOX =4
    const val TYPE_SEND = 5

    enum class TypeField (val code: Int){
        TEXT(1),
        TELNUMBER(2),
        EMAIL(3);

        companion object {
            //fun from (value: Int?) : TypeField? = values().find{ it.code == value }
            fun from (value: String?) : TypeField? = values().find{ it.name == value || it.code == value?.toIntOrNull() }

        }
    }
}