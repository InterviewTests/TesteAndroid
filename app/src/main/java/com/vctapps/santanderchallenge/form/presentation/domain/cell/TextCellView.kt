package com.vctapps.santanderchallenge.form.presentation.domain.cell

import android.view.ViewGroup
import android.widget.TextView
import com.vctapps.santanderchallenge.R
import com.vctapps.santanderchallenge.form.domain.cell.Cell
import com.vctapps.santanderchallenge.form.presentation.domain.cell.base.CellView

class TextCellView(private val textCell: Cell,
                   private val rootView: ViewGroup): CellView(textCell, rootView) {

    init {
        inflateView()
    }

    override fun inflateView() {
        inflateLayout(R.layout.text_cell_view)

        (this.view as TextView).text = textCell.message
    }

}