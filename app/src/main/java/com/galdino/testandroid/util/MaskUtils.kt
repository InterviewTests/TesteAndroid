package com.galdino.testandroid.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

object MaskUtils {
    fun insert(mask: String, ediTxt: EditText, onAfterTextChanged: OnAfterTextChanged?): TextWatcher {
        return object : TextWatcher {
            var isUpdating: Boolean = false
            var old = ""

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                val str = unmask(s.toString())
                var mascara = ""

                if (isUpdating) {
                    old = str
                    isUpdating = false
                    return
                }

                var i = 0
                for (m in mask.toCharArray()) {
                    if (m != '#' && str.length > old.length) {
                        mascara += m
                        continue
                    }
                    try {
                        mascara += str[i]
                    } catch (e: Exception) {
                        break
                    }

                    i++
                }

                isUpdating = true
                ediTxt.setText(mascara)
                ediTxt.setSelection(mascara.length)
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun afterTextChanged(s: Editable) {
                onAfterTextChanged?.afterTextChanged(s)
            }
        }
    }

    fun unmask(s: String): String {
        return s.replace("[.]".toRegex(), "").replace("[-]".toRegex(), "")
                .replace("[/]".toRegex(), "").replace("[(]".toRegex(), "")
                .replace("[)]".toRegex(), "")
    }

    interface OnAfterTextChanged {
        fun afterTextChanged(s: Editable)
    }
}