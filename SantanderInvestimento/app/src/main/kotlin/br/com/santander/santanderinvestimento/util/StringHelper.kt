package br.com.santander.santanderinvestimento.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.text.DecimalFormat


class StringHelper {
    companion object {
        fun formatDoubleToString(valor: Double?): String {
            if (valor == null || valor == 0.0)
                return "0,00"
            var df = DecimalFormat("#.00")
            if (valor > 0.0 && valor < 1.0)
                df = DecimalFormat("0.00")
            return String.format("%s%% ", df.format(valor))

        }

        fun adicionaMascaraTelefoneCadastro(ediTxt: EditText): TextWatcher {
            try {
                return object : TextWatcher {
                     var isUpdating: Boolean = false
                     var old = ""
                     var isDeleting = false

                    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                        val str = unmaskString(s.toString())
                        var mascara = ""
                        if (isUpdating || isDeleting) {
                            old = str
                            isUpdating = false
                            return
                        }
                        isUpdating = true
                        var i = 0
                        var newmask = ""
                        if (str.length in 1..10)
                            newmask = "(##)####-####"
                        else if (str.length >= 11)
                            newmask = "(##)#####-####"
                        for (m in newmask.toCharArray()) {
                            if (m != '#' && str.length > old.length) {
                                mascara += m
                                continue
                            }
                            try {
                                mascara += str.get(i)
                            } catch (e: Exception) {
                                break
                            }

                            i++
                        }

                        if (str != mascara) {

                            ediTxt.setText(mascara)
                            ediTxt.setSelection(mascara.length)
                        }
                        isUpdating = false

                    }

                    override fun beforeTextChanged(charSequence: CharSequence, start: Int, count: Int, after: Int) {
                        isDeleting = count > after
                    }

                    override fun afterTextChanged(s: Editable) {}

                }

            } catch (e: Exception) {
                return object : TextWatcher {
                    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

                    }

                    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                    }

                    override fun afterTextChanged(s: Editable) {

                    }
                }
            }
        }

        fun unmaskString(s: String?): String {
            return if (s == null || s == "")
                ""
            else
                s.replace("[.]".toRegex(), "").replace("[-]".toRegex(), "")
                        .replace("[/]".toRegex(), "").replace("[(]".toRegex(), "")
                        .replace("[)]".toRegex(), "").replace("[:]".toRegex(), "")
        }
    }
}