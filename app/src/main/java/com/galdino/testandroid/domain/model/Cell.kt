package com.galdino.testandroid.domain.model

import com.google.gson.annotations.SerializedName

typealias CellType = Int
typealias CellTypeField = Any

data class Cell(@SerializedName("id")
           val id: Int? = null,

           @SerializedName("type")
           val type: Int,

           @SerializedName("message")
           val message: String? = null,

           @SerializedName("typefield")
           val typefield: Any? = null,

           @SerializedName("hidden")
           val hidden: Boolean? = null,

           @SerializedName("topSpacing")
           val topSpacing: Double? = null,

           @SerializedName("show")
           val show: Any? = null,

           @SerializedName("required")
           val required: Boolean? = null,

            var cellAnswer: CellAnswer? = null){

    object Type
    {
        val FIELD: CellType = 1
        val TEXT: CellType = 2
        val IMAGE: CellType = 3
        val CHECK_BOX: CellType = 4
        val SEND: CellType = 5
    }

    object TypeField{
        val TEXT: CellTypeField = 1
        val TELL_NUMBER: CellTypeField = 2
        val EMAIL: CellTypeField = 3
        val TELL_NUMBER_S: CellTypeField = "telnumber"
    }
}