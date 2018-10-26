package br.com.andreyneto.testesantander.ui.components

import android.content.Context
import android.graphics.PorterDuff
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.support.v4.content.ContextCompat
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.Patterns
import android.view.ContextThemeWrapper
import android.view.ViewGroup
import android.widget.LinearLayout
import br.com.andreyneto.testesantander.R
import br.com.andreyneto.testesantander.ui.PhoneTextWatcher


class CustomTextInputLayout(context: Context) : TextInputLayout(ContextThemeWrapper(context, R.style.EditText)), TextWatcher {
    private var mInputType: String? = null

    override fun afterTextChanged(p0: Editable?) {
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        if(!p0.isNullOrEmpty()) {
            if(mInputType!! == "3" && !Patterns.EMAIL_ADDRESS.matcher(p0).matches()) setError(context.getString(R.string.erro_email))
            else removeError()
        } else setError()
    }

    private fun removeError() {
        this.isErrorEnabled = false
        this.error = null
        editText!!.background.setColorFilter(ContextCompat.getColor(context, R.color.green), PorterDuff.Mode.SRC_ATOP)
    }

    private fun setError(message: String = " ") {
        if(!this.isErrorEnabled)this.isErrorEnabled = true
        this.error = message
        editText!!.background.setColorFilter(ContextCompat.getColor(context, R.color.error), PorterDuff.Mode.SRC_ATOP)
    }

    private var editText: TextInputEditText? = null

    fun setHint(text: String) {
        if (editText == null) {
            editText = TextInputEditText(context)
        }
        editText!!.hint = text
        editText!!.inputType = InputType.TYPE_CLASS_TEXT
        editText!!.maxLines = 1
        editText!!.setTextColor(ContextCompat.getColor(context, R.color.colorAccent))
        val editTextParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        editText!!.layoutParams = editTextParams
        editText!!.addTextChangedListener(this)
        this.addView(editText, editTextParams)
    }

    fun setInputType(inputType: String) {
        mInputType = inputType
        if(mInputType!! == "3"){
            editText!!.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        } else if(mInputType!! == "telnumber") {
            editText!!.inputType = InputType.TYPE_CLASS_PHONE
            editText!!.addTextChangedListener(PhoneTextWatcher(editText!!))
        }
    }

}
