package com.rafhack.testeandroid.form

import android.content.res.Resources
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.LinearLayout
import com.rafhack.testeandroid.data.entities.Cell
import com.rafhack.testeandroid.data.entities.Type
import com.rafhack.testeandroid.form.customCells.CustomCellType1
import com.rafhack.testeandroid.form.customCells.CustomCellType2
import com.rafhack.testeandroid.form.customCells.CustomCellType4
import com.rafhack.testeandroid.form.customCells.CustomCellType5

class DynamicFormManager(val container: LinearLayout) {

    var cells: ArrayList<Cell> = arrayListOf()
        set(value) = populateCells(value)
    private var cellViewMap: HashMap<Int?, View?> = hashMapOf()

    private fun populateCells(value: ArrayList<Cell>) {
        value.forEach { cell ->
            val type = Type.from(cell.type)
            val cellView = when (type) {
                Type.TEXT -> getTextTypeCell(cell)
                Type.FIELD -> getFieldTypeCell(cell)
                Type.CHECKBOX -> getCheckBoxTypeCell(cell)
                Type.SEND -> getButtonTypeCell(cell)
                else -> null
            }
            cellViewMap[cell.id] = cellView
            container.addView(cellView)
            (cellView?.layoutParams as LinearLayout.LayoutParams).topMargin =
                    (cell.topSpacing * Resources.getSystem().displayMetrics.density).toInt()
            cellView.visibility = if (cell.hidden) GONE else VISIBLE
        }
    }

    private fun getCheckBoxTypeCell(cell: Cell): View {
        val cellView = CustomCellType4(container.context)
        cellView.cell = cell

        cellView.chkCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (cell.show != null)
                cellViewMap[cell.show]?.visibility = if (isChecked) VISIBLE else GONE
        }

        return cellView
    }

    private fun getButtonTypeCell(cell: Cell): View {
        val cellView = CustomCellType5(container.context)
        cellView.cell = cell
        return cellView
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