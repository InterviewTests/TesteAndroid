package com.santander.luizlago.testeandroid.api.models

data class Cell(
        val id: Int,
        val type: Int,
        val message: String,
        val typefield: String?,
        val hidden: Boolean,
        val topSpacing: Float,
        val show: Int?,
        val require: Boolean)