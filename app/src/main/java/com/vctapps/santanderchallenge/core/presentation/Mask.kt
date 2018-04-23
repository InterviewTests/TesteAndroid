package com.vctapps.santanderchallenge.core.presentation

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

object Mask {

    fun getTelNumberMask(edittext: EditText): TextWatcher{
        return object : TextWatcher{
            var oldText = ""
            var isUpdating = false

            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val textClear = removeMask(text.toString())

                val mask = if(textClear.length == 10) "(##) ####-####" else "(##) #####-####"

                var textWithMask = ""

                if (isUpdating) {
                    oldText = textClear
                    isUpdating = false
                    return
                }
                var i = 0

                for (m in mask.toCharArray()) {
                    if (m != '#' && textClear.length > oldText.length) {
                        textWithMask += m
                        continue
                    }
                    try {
                        textWithMask += textClear.get(i)
                    } catch (e: Exception) {
                        break
                    }

                    i++
                }

                isUpdating = true
                edittext.setText(textWithMask)
                edittext.setSelection(textWithMask.length)
            }
        }
    }

    fun removeMask(s: String): String {
        return s.replace("[.]".toRegex(), "")
                .replace("[-]".toRegex(), "")
                .replace("[/]".toRegex(), "")
                .replace("[(]".toRegex(), "")
                .replace("[ ]".toRegex(), "")
                .replace("[:]".toRegex(), "")
                .replace("[)]".toRegex(), "")
    }

}