package com.fuinha11.test.testeandroid.data.model

import com.google.gson.annotations.SerializedName

data class Cell (
        val id: Int,
        val type: Int,
        val message: String,
        @SerializedName("typefield")
        val typeField: String?,
        val hidden: Boolean,
        val topSpacing: Double,
        val show: Int?,
        val required: Boolean
)