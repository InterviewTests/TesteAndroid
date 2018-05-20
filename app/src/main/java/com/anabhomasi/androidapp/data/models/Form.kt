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
}