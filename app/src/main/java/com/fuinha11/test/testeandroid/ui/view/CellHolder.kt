package com.fuinha11.test.testeandroid.ui.view

import android.content.Context
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.*
import com.fuinha11.test.testeandroid.R
import com.fuinha11.test.testeandroid.data.model.Cell
import com.fuinha11.test.testeandroid.util.extension.isValidEmail
import com.fuinha11.test.testeandroid.util.extension.isValidPhoneNumber
import com.fuinha11.test.testeandroid.util.extension.isValidText

class CellHolder(private val cell: Cell, context: Context) {

    val view: View

    init {

        when (cell.type) {
            Cell.CellType.text -> {
                view = TextView(context, null, R.style.CellTextView_Dark)
                view.text = cell.message
            }
            Cell.CellType.checkbox -> {
                view = CheckBox(context, null, R.style.Checkbox_Dark)
                view.text = cell.message
            }
            Cell.CellType.send -> {
                view = Button(context, null, R.style.Button_Dark)
                view.text = cell.message
            }
            else -> {
                view = View(context)
            }
        }


        val params = LinearLayout.LayoutParams(WRAP_CONTENT, MATCH_PARENT)
        params.setMargins(0, cell.topSpacing.toInt(), 0, 0)
        view.layoutParams = params

        if (cell.hidden)
            view.visibility = View.GONE

    }

    fun isValid(): Boolean {

        if (!cell.required)
            return true

        var valid = true

        if (cell.type == Cell.CellType.field) {
            val text = (view as EditText).text.toString()
            when (cell.typeField) {
                Cell.TypeField.text -> valid = text.isValidText()
                Cell.TypeField.email -> valid = text.isValidEmail()
                Cell.TypeField.telNumber -> valid = text.isValidPhoneNumber()
            }
        } else if (cell.type == Cell.CellType.checkbox) {
            valid = (view as CheckBox).isChecked
        }

        return valid
    }

    fun setCallback(fn: () -> Unit) {
        view.setOnClickListener { fn() }
    }
}