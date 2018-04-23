package br.com.cesarsicas.stdandroidteste.mvp.form

import android.support.design.widget.TextInputLayout
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import br.com.cesarsicas.stdandroidteste.constants.CellTypeField
import br.com.cesarsicas.stdandroidteste.domains.Cell

/**
 * Created by julio on 4/22/18.
 */
interface FormView {
    fun showError(message: String?)
    fun addCells(cells: List<Cell>)
    fun generateButton(cell: Cell?): Button
    fun generateTextView(cell: Cell?): TextView
    fun generateEditText(cell: Cell?): TextInputLayout
    fun configureMargin(view: View, cell: Cell?)
    fun configureVisibility(view: View, cell: Cell?)
    fun makeMask(editText: EditText, typeField: CellTypeField?)
    fun generateCheckBox(cell: Cell?): View?
    fun addView(view: View)
}