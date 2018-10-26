package br.com.andreyneto.testesantander.ui

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class PhoneTextWatcher(private val mEditText: EditText) : TextWatcher {

    internal val MAX_LENGTH = 11

    private var mFormatting: Boolean = false
    private var clearFlag: Boolean = false
    private var mLastStartLocation: Int = 0
    private var mLastBeforeText: String? = null

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int,
                                   after: Int) {
        if (after == 0 && s.toString() == "(") {
            clearFlag = true
        }
        mLastStartLocation = start
        mLastBeforeText = s.toString()
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(s: Editable) {
        if (!mFormatting) {
            mFormatting = true
            val curPos = mLastStartLocation
            val beforeValue = mLastBeforeText
            val currentValue = s.toString()
            val formattedValue = formatUsNumber(s)
            if (currentValue.length > beforeValue!!.length) {
                val setCusorPos = formattedValue.length - (beforeValue.length - curPos)
                mEditText.setSelection(if (setCusorPos < 0) 0 else setCusorPos)
            } else {
                var setCusorPos = formattedValue.length - (currentValue.length - curPos)
                if (setCusorPos > 0 && !Character.isDigit(formattedValue[setCusorPos - 1])) {
                    setCusorPos--
                }
                mEditText.setSelection(if (setCusorPos < 0) 0 else setCusorPos)
            }
            mFormatting = false
        }
    }

    private fun formatUsNumber(text: Editable): String {
        val formattedString = StringBuilder()

        var p = 0
        while (p < text.length) {
            val ch = text[p]
            if (!Character.isDigit(ch)) {
                text.delete(p, p + 1)
            } else {
                p++
            }
        }

        var allDigitString = text.toString()

        var totalDigitCount = allDigitString.length

        if (totalDigitCount > MAX_LENGTH) {
            allDigitString = allDigitString.substring(0, MAX_LENGTH)
            totalDigitCount--
        }

        val isLonger = totalDigitCount == MAX_LENGTH
        val dashAfter = if (isLonger) 5 else 4

        if (totalDigitCount == 0
                || totalDigitCount > 11 && !allDigitString.startsWith("(")
                || totalDigitCount > 12) {
            text.clear()
            text.append(allDigitString)
            return allDigitString
        }

        var alreadyPlacedDigitCount = 0
        if (allDigitString == "(" && clearFlag) {
            text.clear()
            clearFlag = false
            return ""
        }

        if (totalDigitCount - alreadyPlacedDigitCount > 2) {
            formattedString.append("("
                    + allDigitString.substring(alreadyPlacedDigitCount,
                    alreadyPlacedDigitCount + 2) + ") ")
            alreadyPlacedDigitCount += 2
        }

        if (totalDigitCount - alreadyPlacedDigitCount > dashAfter) {
            formattedString.append(allDigitString.substring(
                    alreadyPlacedDigitCount, alreadyPlacedDigitCount + dashAfter) + "-")
            alreadyPlacedDigitCount += dashAfter
        }

        if (totalDigitCount > alreadyPlacedDigitCount) {
            formattedString.append(allDigitString
                    .substring(alreadyPlacedDigitCount))
        }

        text.clear()
        text.append(formattedString.toString())
        return formattedString.toString()
    }
}