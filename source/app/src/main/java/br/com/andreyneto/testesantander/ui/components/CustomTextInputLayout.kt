package br.com.andreyneto.testesantander.ui.components

import android.content.Context
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.support.v4.content.ContextCompat
import android.view.ContextThemeWrapper
import android.view.ViewGroup
import android.widget.LinearLayout
import br.com.andreyneto.testesantander.R

class CustomTextInputLayout(context: Context) : TextInputLayout(ContextThemeWrapper(context, R.style.EditText)) {

    private var editText: TextInputEditText? = null

    fun setHint(text: String) {
        if (editText == null) {
            editText = TextInputEditText(context)
        }
        editText!!.hint = text
        editText!!.setTextColor(ContextCompat.getColor(context, R.color.colorAccent))
        val editTextParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        editText!!.layoutParams = editTextParams
        this.addView(editText, editTextParams)
    }

    fun setInputType(inputType: Int) {
        editText!!.inputType = inputType
    }

}
