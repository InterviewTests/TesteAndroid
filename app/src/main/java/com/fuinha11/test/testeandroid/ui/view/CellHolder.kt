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
import kotlin.coroutines.experimental.coroutineContext

class CellHolder(val cell: Cell, context: Context) {
    lateinit var button: Button
    lateinit var editText: EditText
    lateinit var textView: TextView
    lateinit var checkBox: CheckBox

    init {

        val params = LinearLayout.LayoutParams(WRAP_CONTENT, MATCH_PARENT)
        params.setMargins(0, cell.topSpacing.toInt(), 0, 0)

        when (cell.type) {
            Cell.CellType.field -> {
                editText = EditText(context, null, R.style.CellTextView_Dark)
                editText.hint = cell.message
//                editText.layoutParams = params
                if (cell.hidden)
                    editText.visibility = View.GONE
            }
            Cell.CellType.text -> {
                textView = TextView(context, null, R.style.CellTextView_Dark)
                textView.text = cell.message
//                textView.layoutParams = params
                if (cell.hidden)
                    textView.visibility = View.GONE
            }
            Cell.CellType.checkbox -> {
                checkBox = CheckBox(context, null, R.style.Checkbox_Dark)
                checkBox.text = cell.message
//                checkBox.layoutParams = params
                if (cell.hidden)
                    checkBox.visibility = View.GONE
            }
            Cell.CellType.send -> {
                button = Button(context, null, R.style.Button_Dark)
                button.text = cell.message
//                button.layoutParams = params
                if (cell.hidden)
                    button.visibility = View.GONE
            }
        }



    }

    fun isValid(): Boolean {

        if (!cell.required)
            return true

        var valid = true

        if (cell.type == Cell.CellType.field) {
            val text = (editText.text.toString())
            when (cell.typeField) {
                Cell.TypeField.text -> valid = text.isValidText()
                Cell.TypeField.email -> valid = text.isValidEmail()
                Cell.TypeField.telNumber -> valid = text.isValidPhoneNumber()
            }
        } else if (cell.type == Cell.CellType.checkbox) {
            valid = checkBox.isChecked
        }

        return valid
    }

    fun setCallback(fn: () -> Unit) {
        getComponent().setOnClickListener { fn() }
    }

    fun checkError() {
        if (cell.type == Cell.CellType.field) {
            if (isValid())
                editText.error = null
            else
                editText.error = "Campo inválido"
        }
    }

    fun getComponent() : View {
        return when (cell.type) {
            Cell.CellType.field -> editText
            Cell.CellType.text -> textView
            Cell.CellType.checkbox -> checkBox
            Cell.CellType.send -> button
            else -> View(null)
        }
    }
}