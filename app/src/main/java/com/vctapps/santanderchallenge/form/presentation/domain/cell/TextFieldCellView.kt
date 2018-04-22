package com.vctapps.santanderchallenge.form.presentation.domain.cell

import android.content.res.ColorStateList
import android.graphics.Color
import android.support.design.widget.TextInputEditText
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat
import android.support.v4.view.ViewCompat
import android.text.InputType
import android.view.ViewGroup
import com.vctapps.santanderchallenge.R
import com.vctapps.santanderchallenge.form.domain.cell.EmailFieldCell
import com.vctapps.santanderchallenge.form.domain.cell.FieldCell
import com.vctapps.santanderchallenge.form.domain.cell.TelNumberFieldCell
import com.vctapps.santanderchallenge.form.domain.cell.TextFieldCell
import com.vctapps.santanderchallenge.form.presentation.domain.cell.base.FieldCellView
import kotlinx.android.synthetic.main.text_field_cell_view.view.*

class TextFieldCellView(private val textFieldCell: FieldCell,
                        private val rootView: ViewGroup): FieldCellView(textFieldCell, rootView) {

    init {
        this.inflateView()
    }

    private lateinit var editText: TextInputEditText

    override fun isValidAnswer(): Boolean {
        val answer = editText.text.toString()

        return textFieldCell.validateResponse(answer)
    }

    override fun showError() {
        val colorState = ColorStateList.valueOf(Color.RED)
        ViewCompat.setBackgroundTintList(editText, colorState)
    }

    override fun hideError() {
        val colorState = ColorStateList.valueOf(Color.BLUE)
        ViewCompat.setBackgroundTintList(editText, colorState)
    }

    override fun inflateView() {
        inflateLayout(R.layout.text_field_cell_view)

        editText = this.view.editTextTextCell

        val textInputLayout = this.view.textInputLayoutTextCell

        textInputLayout.hint = textFieldCell.message

        when(textFieldCell){
            is EmailFieldCell -> editText.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
            is TelNumberFieldCell -> editText.inputType = InputType.TYPE_CLASS_PHONE
            is TextFieldCell -> editText.inputType = InputType.TYPE_CLASS_TEXT
        }

        val colorStateList = ColorStateList.valueOf(ContextCompat.getColor(rootView.context, R.color.gray_600))
        ViewCompat.setBackgroundTintList(editText, colorStateList)
    }
}