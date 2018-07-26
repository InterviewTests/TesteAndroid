package com.rafhack.testeandroid.data.entities

data class Cell(
        var id: Int = 0,
        var type: Int = 0,
        var message: String = "",
        var typeField: Int? = 0,
        var hidden: Boolean = false,
        var topSpacing: Int = 0,
        var show: Int? = 0,
        var required: Boolean = false
)