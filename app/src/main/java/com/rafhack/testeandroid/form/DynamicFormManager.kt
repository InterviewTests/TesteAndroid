package com.rafhack.testeandroid.form

import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.LinearLayout
import com.rafhack.testeandroid.data.entities.Cell
import com.rafhack.testeandroid.data.entities.Type
import com.rafhack.testeandroid.form.customCells.CustomCellType1

class DynamicFormManager(val container: LinearLayout) {

    var cells: ArrayList<Cell> = arrayListOf()
        set(value) = populateCells(value)

    private fun populateCells(value: ArrayList<Cell>) {
        value.forEach { cell ->
            val type = Type.from(cell.type)
            when (type) {
                Type.TEXT -> addFieldTypeCell(cell)
                Type.FIELD -> addFieldTypeCell(cell)
                else -> {
                }
            }
        }
    }

    private fun addFieldTypeCell(cell: Cell) {
        val cellView = CustomCellType1(container.context)
        cellView.cell = cell
        container.addView(cellView)
        (cellView.layoutParams as LinearLayout.LayoutParams).topMargin = cell.topSpacing
        cellView.visibility = if (cell.hidden) GONE else VISIBLE
    }
}