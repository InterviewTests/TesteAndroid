package com.vctapps.santanderchallenge.form.presentation.domain.cell

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.vctapps.santanderchallenge.R
import com.vctapps.santanderchallenge.form.domain.cell.Cell
import com.vctapps.santanderchallenge.form.presentation.domain.cell.base.CellView

class SendCellView(private val sendFieldCell: Cell,
                   private val rootView: ViewGroup): CellView(sendFieldCell, rootView) {

    init {
        this.inflateView()
    }

    override fun inflateView() {
        inflateLayout(R.layout.send_cell_view)

        (this.view as Button).text = sendFieldCell.message
    }

    fun setSendListener(clickListener: View.OnClickListener){
        this.view.setOnClickListener(clickListener)
    }

}