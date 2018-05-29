package andcordeiro.com.br.testeandroid.system.extensions

import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.EditText

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object: TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            afterTextChanged.invoke(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
    })
}

fun EditText.validate(validator: (String) -> Boolean, colorError: Int, colorSuccess: Int) {
    this.afterTextChanged {
        if (validator(it))
            this.background.setColorFilter(colorSuccess, android.graphics.PorterDuff.Mode.SRC_IN)
        else
            this.background.setColorFilter(colorError, android.graphics.PorterDuff.Mode.SRC_IN)
    }
}



fun String.isValidEmail(): Boolean
        = this.isNotEmpty() &&
        Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.isValidText(): Boolean
        = this.isNotEmpty()

fun String.isValidNumber(): Boolean
        = this.isNotEmpty() &&
        this.length > 13











