package com.galdino.testandroid.data.entity

import com.galdino.testandroid.domain.model.Cell
import com.google.gson.annotations.SerializedName

data class CellResponseBody(
        @SerializedName("cells") val cells: List<Cell>)
{

}