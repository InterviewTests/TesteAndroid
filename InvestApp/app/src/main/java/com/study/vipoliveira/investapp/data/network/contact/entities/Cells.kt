package com.study.vipoliveira.investapp.data.network.contact.entities

import com.google.gson.annotations.SerializedName

data class Cells (
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