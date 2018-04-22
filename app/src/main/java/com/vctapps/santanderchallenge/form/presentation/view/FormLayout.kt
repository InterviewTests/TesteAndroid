package com.vctapps.santanderchallenge.form.presentation.view

import android.animation.LayoutTransition
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.vctapps.santanderchallenge.form.presentation.domain.cell.SendCellView
import com.vctapps.santanderchallenge.form.presentation.domain.cell.base.CellView
import com.vctapps.santanderchallenge.form.presentation.domain.cell.base.FieldCellView

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

    fun clearFields(){
        listCellView.forEach { cell ->
            if (cell is FieldCellView) {
                cell.hideError()
                cell.clearField()
            }
        }
    }

    fun checkErrors(): Boolean {
        var hasError = false

        listCellView.forEach { cell ->
            if(cell is FieldCellView && !cell.isValidAnswer()) {
                hasError = true
            }
        }

        return hasError
    }

    fun hideErrors(){
        listCellView.forEach { cell ->
            if (cell is FieldCellView) {
                cell.hideError()
            }
        }
    }

    fun showErrors(){
        listCellView.forEach { cell ->
            if (cell is FieldCellView && !cell.isValidAnswer()) {
                cell.showError()
            }
        }
    }

    fun setSendButtonListener(listener: View.OnClickListener){

        listCellView.forEach { cell ->

            (cell as? SendCellView)?.setSendListener(listener)

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