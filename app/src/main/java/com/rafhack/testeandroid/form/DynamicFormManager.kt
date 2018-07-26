package com.rafhack.testeandroid.form

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.LinearLayout
import com.rafhack.testeandroid.data.entities.Cell
import com.rafhack.testeandroid.data.entities.Type
import com.rafhack.testeandroid.form.customCells.CustomCellType1
import com.rafhack.testeandroid.form.customCells.CustomCellType2

class DynamicFormManager(val container: LinearLayout) {

    var cells: ArrayList<Cell> = arrayListOf()
        set(value) = populateCells(value)

    private fun populateCells(value: ArrayList<Cell>) {
        value.forEach { cell ->
            val type = Type.from(cell.type)
            val cellView = when (type) {
                Type.TEXT -> getTextTypeCell(cell)
                Type.FIELD -> getFieldTypeCell(cell)
                else -> null
            }
            container.addView(cellView)
            (cellView?.layoutParams as LinearLayout.LayoutParams).topMargin = cell.topSpacing
            cellView.visibility = if (cell.hidden) GONE else VISIBLE
        }
    }

    private fun getTextTypeCell(cell: Cell): View {
        val cellView = CustomCellType2(container.context)
        cellView.cell = cell
        return cellView
    }

    private fun getFieldTypeCell(cell: Cell): View {
        val cellView = CustomCellType1(container.context)
        cellView.cell = cell
        return cellView
    }
}