package com.vctapps.santanderchallenge.form.presentation.domain.cell

import android.widget.LinearLayout
import com.vctapps.santanderchallenge.form.domain.Type
import com.vctapps.santanderchallenge.form.domain.cell.Cell
import com.vctapps.santanderchallenge.form.domain.cell.FieldCell
import com.vctapps.santanderchallenge.form.presentation.domain.cell.base.CellView
import com.vctapps.santanderchallenge.form.presentation.view.FormLayout

class FormViewBuilder(private val rootView: FormLayout) {

    fun proccessCellsFormView(cells: MutableList<Cell>): MutableList<CellView>{
        val listCellView = mutableListOf<CellView>()

        cells.forEach { cell ->
            val cellView = getCellView(cell)

            rootView.addView(cellView.getCellView())

            listCellView.add(cellView)
        }

        return listCellView
    }

    private fun getCellView(cell: Cell): CellView {
        return when (cell.type) {
            Type.FIELD -> TextFieldCellView(cell as FieldCell, rootView)
            Type.TEXT -> TextCellView(cell, rootView)
            Type.CHECKBOX -> CheckboxCellView(cell, rootView)
            Type.SEND -> SendCellView(cell, rootView)
            else -> TextCellView(cell, rootView)
        }
    }

}