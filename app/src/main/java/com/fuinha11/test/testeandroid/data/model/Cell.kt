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
                @SerializedName("1")
                field(1),
                @SerializedName("2")
                text(2),
                @SerializedName("3")
                image(3),
                @SerializedName("4")
                checkbox(4),
                @SerializedName("5")
                send(5)
        }

        enum class TypeField(t: Int) {
                @SerializedName("1")
                text(1),
                @SerializedName("2")
                telNumber(2),
                @SerializedName("3")
                email(3)
        }
}
