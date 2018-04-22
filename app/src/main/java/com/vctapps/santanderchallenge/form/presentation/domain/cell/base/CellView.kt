package com.vctapps.santanderchallenge.form.presentation.domain.cell.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.vctapps.santanderchallenge.core.domain.InvalidData
import com.vctapps.santanderchallenge.form.domain.cell.Cell
import com.vctapps.santanderchallenge.form.presentation.view.OnShowViewRequest

abstract class CellView(val cell: Cell, private val rootView: ViewGroup) {

    protected lateinit var view: View

    protected var onShowViewRequest: OnShowViewRequest? = null

    abstract fun inflateView()

    fun getCellView() = view

    protected fun inflateLayout(layoutId: Int){
        this.view = LayoutInflater
                .from(rootView.context)
                .inflate(layoutId, rootView, false)

        setMargins()

        if(cell.hidden){
            this.view.visibility = View.GONE
        }
    }

    private fun setMargins(){
        val params = this.view.layoutParams as LinearLayout.LayoutParams

        params.setMargins(0, cell.topSpacing.toInt(), 0, 0)
    }

    fun setShowViewRequest(listener: OnShowViewRequest){
        if(cell.show != InvalidData.INVALID_INT){
            onShowViewRequest = listener
        }
    }

}