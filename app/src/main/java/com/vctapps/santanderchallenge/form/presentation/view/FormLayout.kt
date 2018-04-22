package com.vctapps.santanderchallenge.form.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.vctapps.santanderchallenge.form.presentation.domain.cell.SendCellView
import com.vctapps.santanderchallenge.form.presentation.domain.cell.base.CellView

class FormLayout(context: Context, attrs: AttributeSet): LinearLayout(context, attrs) {

    var listCellView = mutableListOf<CellView>()

    fun setCellsView(cells: MutableList<CellView>){

        listCellView = cells

        listCellView.forEach { cell -> addView(cell.getCellView()) }

    }

    fun setSendButtonListener(listener: View.OnClickListener){

        listCellView.forEach { cell ->

            if(cell is SendCellView) cell.setSendListener(listener)

        }

    }

}