package com.galdino.testandroid.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.galdino.testandroid.domain.model.Cell
import com.galdino.testandroid.domain.model.CellAnswer

object MaskUtils {
    var changePhone = false

    fun initializePhoneMask(mask: String, field: EditText, cell: Cell) {
        var founded = false
        var maskChanged = false
        val phoneMaskListener :MaskUtils.OnAfterTextChanged = object : MaskUtils.OnAfterTextChanged {
            override fun afterTextChanged(s: Editable) {
                if (s.length == 13 && maskChanged) {
                    maskChanged = false
                    MaskUtils.changePhoneMask(field,s.toString(),MasksType.PHONE_NUMBER)
                }
                if (s.length != mask.length) {
                    founded = false
                    return
                }
                if (!founded) {
                    founded = true

                    if (s.length == 14 && !maskChanged) {
                        maskChanged = true
                        MaskUtils.changePhoneMask(field,s.toString(),MasksType.CEL_NUMBER)
                    }
                    cell.cellAnswer = CellAnswer(text = s.toString())
                }
            }
        }
        val textWatcher: TextWatcher = MaskUtils.insertPhoneMask(mask, field, phoneMaskListener)
        field.addTextChangedListener(textWatcher)
    }

    private fun insertPhoneMask(mask: String, ediTxt: EditText, onAfterTextChanged: OnAfterTextChanged?): TextWatcher {
        return object : TextWatcher {
            var isUpdating: Boolean = false
            var old = ""

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                val str = unmask(s.toString())
                var mascara = ""
                if(!changePhone) {
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
                else{
                    changePhone = false
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun afterTextChanged(s: Editable) {
                onAfterTextChanged?.afterTextChanged(s)
            }
        }
    }

    fun initializeTextWatcher(field: EditText, cell: Cell)
    {
        field.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                cell.cellAnswer = CellAnswer(text = p0.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })
    }

    fun unmask(s: String): String {
        return s.replace("[.]".toRegex(), "").replace("[-]".toRegex(), "")
                .replace("[/]".toRegex(), "").replace("[(]".toRegex(), "")
                .replace("[)]".toRegex(), "")
    }

    fun changePhoneMask(field: EditText, text: String, mask: String)
    {
        val str = unmask(text)
        var mascara = ""
        var i = 0
        for (m in mask.toCharArray()) {

            if (m != '#') {
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
        changePhone = true
        field.setText(mascara)
        field.setSelection(mascara.length)

    }

    interface OnAfterTextChanged {
        fun afterTextChanged(s: Editable)
    }
}