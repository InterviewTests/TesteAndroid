package com.vctapps.santanderchallenge.form.presentation.domain.cell.base

import android.view.ViewGroup
import com.vctapps.santanderchallenge.form.domain.cell.FieldCell

abstract class FieldCellView(val fieldCell: FieldCell,
                             private val rootView: ViewGroup): CellView(fieldCell, rootView){

    abstract fun isValidAnswer(): Boolean

    abstract fun showError()

    abstract fun hideError()

    abstract fun clearField()

}