package com.vctapps.santanderchallenge.form.data

import com.vctapps.santanderchallenge.core.domain.InvalidData
import com.vctapps.santanderchallenge.form.data.entity.CellJsonResponse
import com.vctapps.santanderchallenge.form.data.entity.CellResponse
import com.vctapps.santanderchallenge.form.domain.cell.Cell
import com.vctapps.santanderchallenge.form.domain.cell.EmailFieldCell
import com.vctapps.santanderchallenge.form.domain.cell.TelNumberFieldCell
import com.vctapps.santanderchallenge.form.domain.cell.TextFieldCell

object CellMapper {

    fun map(cellJsonResponse: CellJsonResponse): MutableList<Cell> {
        val cellList = mutableListOf<Cell>()

        cellJsonResponse.cells.mapTo(cellList, { cellResponse -> map(cellResponse)})

        return cellList
    }

    fun map(cellResponse: CellResponse): Cell {
        return when(cellResponse.typeField){
            1.0 -> getTextFieldCell(cellResponse)
            2.0 -> getTelNumberFieldCell(cellResponse)
            3.0 -> getEmailFieldCell(cellResponse)
            "telnumber" -> getTelNumberFieldCell(cellResponse)
            else -> getSimpleCell(cellResponse)
        }
    }

    private fun getSimpleCell(cellResponse: CellResponse) =
            Cell(
                    cellResponse.id ?: InvalidData.INVALID_INT,
                    cellResponse.type ?: InvalidData.INVALID_INT,
                    cellResponse.message ?: InvalidData.INVALID_STRING,
                    getCorrectlyTypeField(cellResponse.typeField),
                    cellResponse.hidden ?: false,
                    cellResponse.topSpacing ?: InvalidData.INVALID_DOUBLE,
                    cellResponse.show ?: InvalidData.INVALID_INT,
                    cellResponse.required ?: false
            )

    private fun getEmailFieldCell(cellResponse: CellResponse) =
        EmailFieldCell(
                cellResponse.id ?: InvalidData.INVALID_INT,
                cellResponse.type ?: InvalidData.INVALID_INT,
                cellResponse.message ?: InvalidData.INVALID_STRING,
                getCorrectlyTypeField(cellResponse.typeField),
                cellResponse.hidden ?: false,
                cellResponse.topSpacing ?: InvalidData.INVALID_DOUBLE,
                cellResponse.show ?: InvalidData.INVALID_INT,
                cellResponse.required ?: false
        )

    private fun getTelNumberFieldCell(cellResponse: CellResponse) =
            TelNumberFieldCell(
                    cellResponse.id ?: InvalidData.INVALID_INT,
                    cellResponse.type ?: InvalidData.INVALID_INT,
                    cellResponse.message ?: InvalidData.INVALID_STRING,
                    getCorrectlyTypeField(cellResponse.typeField),
                    cellResponse.hidden ?: false,
                    cellResponse.topSpacing ?: InvalidData.INVALID_DOUBLE,
                    cellResponse.show ?: InvalidData.INVALID_INT,
                    cellResponse.required ?: false
            )

    private fun getTextFieldCell(cellResponse: CellResponse) =
            TextFieldCell(
                    cellResponse.id ?: InvalidData.INVALID_INT,
                    cellResponse.type ?: InvalidData.INVALID_INT,
                    cellResponse.message ?: InvalidData.INVALID_STRING,
                    getCorrectlyTypeField(cellResponse.typeField),
                    cellResponse.hidden ?: false,
                    cellResponse.topSpacing ?: InvalidData.INVALID_DOUBLE,
                    cellResponse.show ?: InvalidData.INVALID_INT,
                    cellResponse.required ?: false
            )

    /**
     * The API send a wrong string type on typeField when is telNumber. Needs a Int how the doc.
     * Others types is normal send as Int
     */
    private fun getCorrectlyTypeField(typeField: Any?): Int{
        return when(typeField){
            is Int -> typeField
            is String -> mapStringTypeField(typeField)
            else -> InvalidData.INVALID_INT
        }
    }

    private fun mapStringTypeField(typeField: String): Int{
        return when(typeField){
            "text" -> 1
            "telnumber" -> 2
            "email" -> 3
            else -> InvalidData.INVALID_INT
        }
    }

}