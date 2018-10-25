package com.fuinha11.test.testeandroid.data.model

import com.google.gson.annotations.SerializedName

data class Cell (
        val id: Int,
        val type: CellType,
        val message: String,
        @SerializedName("typefield")
        val typeField: TypeField?,
        val hidden: Boolean,
        val topSpacing: Double,
        val show: Int?,
        val required: Boolean
) {

        enum class CellType(t: Int) {
                field(1),
                text(2),
                image(3),
                checkbox(4),
                send(5)
        }

        enum class TypeField(t: Int) {
                text(1),
                telNumber(2),
                email(3)
        }
}
