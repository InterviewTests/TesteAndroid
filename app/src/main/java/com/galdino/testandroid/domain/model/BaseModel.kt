package com.galdino.testandroid.domain.model

import android.content.Context
import android.graphics.PorterDuff
import android.support.v4.content.ContextCompat
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.galdino.testandroid.R

open class BaseModel {
    protected fun showErrorWithAnimation(context: Context?, editText: EditText?, msg: Int) {
        if (context != null) {
            Toast.makeText(context, context.getString(msg), Toast.LENGTH_SHORT).show()

            YoYo.with(Techniques.Shake).playOn(editText)
            val color = ContextCompat.getColor(context, R.color.colorHintError)
            editText?.let {
                it.requestFocus()
                it.background.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
                val watcher = object : TextWatcher {
                    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

                    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

                    override fun afterTextChanged(s: Editable) {
                        it.background.clearColorFilter()
                    }
                }
                it.addTextChangedListener(watcher)
            }
        }
    }
}