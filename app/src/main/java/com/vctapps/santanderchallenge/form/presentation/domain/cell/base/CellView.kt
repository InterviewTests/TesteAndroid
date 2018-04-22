package com.vctapps.santanderchallenge.form.presentation.domain.cell.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.vctapps.santanderchallenge.form.domain.cell.Cell

abstract class CellView(private val cell: Cell, private val rootView: ViewGroup) {

    protected lateinit var view: View

    abstract fun inflateView()

    fun getCellView() = view

    protected fun inflateLayout(layoutId: Int){
        this.view = LayoutInflater
                .from(rootView.context)
                .inflate(layoutId, rootView, false)

        setMargins()
    }

    private fun setMargins(){
        val params = this.view.layoutParams as LinearLayout.LayoutParams

        params.setMargins(0, cell.topSpacing.toInt(), 0, 0)
    }

}