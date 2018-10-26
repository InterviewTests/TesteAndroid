package com.fuinha11.test.testeandroid.ui.view

import android.app.Activity
import android.text.Editable
import android.text.InputType
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.*
import com.fuinha11.test.testeandroid.R
import com.fuinha11.test.testeandroid.data.model.Cell
import com.fuinha11.test.testeandroid.util.extension.isValidEmail
import com.fuinha11.test.testeandroid.util.extension.isValidPhoneNumber
import com.fuinha11.test.testeandroid.util.extension.isValidText

class CellHolder(val cell: Cell, val activity: Activity) {

    val view: View


    init {

        view = when (cell.type) {
            Cell.CellType.field -> initEditText()
            Cell.CellType.text -> initTextView()
            Cell.CellType.checkbox -> initCheckbox()
            Cell.CellType.send -> initButton()
            else -> View(activity)
        }

        val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        params.setMargins(0, cell.topSpacing.toInt(), 0, 0)

        view.layoutParams = params
    }

    private fun initButton(): View {
        val button = activity.layoutInflater.inflate(R.layout.button_custom, null, false) as Button
        button.text = cell.message
        return button
    }

    private fun initCheckbox(): View {
        val checkBox = activity.layoutInflater.inflate(R.layout.check_box_custom, null, false) as CheckBox
        checkBox.text = cell.message
        return checkBox
    }

    private fun initTextView(): View {
        val textView = activity.layoutInflater.inflate(R.layout.text_view_custom, null, false) as TextView
        textView.text = cell.message
        return textView
    }

    private fun initEditText(): View {
        val editText = activity.layoutInflater.inflate(R.layout.edit_text_custom, null, false) as EditText
        editText.hint = cell.message
        if (cell.hidden) editText.visibility = View.GONE else editText.visibility = View.VISIBLE
        when (cell.typeField) {
            Cell.TypeField.text -> editText.inputType = InputType.TYPE_CLASS_TEXT

            Cell.TypeField.telNumber -> editText.inputType = InputType.TYPE_CLASS_PHONE

            Cell.TypeField.email -> editText.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        }

        return editText
    }

    fun isValid(): Boolean {

        if (!cell.required || view.visibility == GONE)
            return true

        var valid = true

        if (cell.type == Cell.CellType.field) {
            val text = ((view as EditText).text.toString())
            when (cell.typeField) {
                Cell.TypeField.text -> valid = text.isValidText()
                Cell.TypeField.email -> valid = text.isValidEmail()
                Cell.TypeField.telNumber -> valid = text.isValidPhoneNumber()
            }
        }

        return valid
    }

    fun setCallback(fn: () -> Unit) {
        view.setOnClickListener { fn() }
    }

    fun checkError() {
        if (view is EditText) {
            if (isValid())
                view.error = null
            else
                view.error = "Campo inválido"
        }
    }

    fun clearText() {
        if (view is EditText) {
            view.setText("")
        }
    }
}