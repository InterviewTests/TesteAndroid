package com.vctapps.santanderchallenge.form.presentation.view

import android.animation.LayoutTransition
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.vctapps.santanderchallenge.form.presentation.domain.cell.SendCellView
import com.vctapps.santanderchallenge.form.presentation.domain.cell.base.CellView

class FormLayout(context: Context, attrs: AttributeSet): LinearLayout(context, attrs), OnShowViewRequest {

    init {
        layoutTransition = LayoutTransition()
    }

    var listCellView = mutableListOf<CellView>()

    fun setCellsView(cells: MutableList<CellView>){

        listCellView = cells

        listCellView.forEach { cell ->
            cell.setShowViewRequest(this)
            addView(cell.getCellView())
        }

    }

    fun setSendButtonListener(listener: View.OnClickListener){

        listCellView.forEach { cell ->

            if(cell is SendCellView) cell.setSendListener(listener)

        }

    }

    override fun showView(id: Int) {
        listCellView.forEach { cellView ->
            if(cellView.cell.id == id){
                cellView.getCellView().visibility = View.VISIBLE
            }
        }
    }

    override fun hideView(id: Int) {
        listCellView.forEach { cellView ->
            if(cellView.cell.id == id){
                cellView.getCellView().visibility = View.GONE
            }
        }
    }
}