package com.study.vipoliveira.investapp.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.lang.ref.WeakReference

/**
 * Created by kevin on 31/01/18.
 * Adapted from:
 * https://gist.github.com/alfredbaudisch/f4416061dd1858b1f4ee
 * http://stackoverflow.com/a/23659268/332839
 */
enum class PhoneNumberFormatType {
    PT_BR, EN_US
}

class PhoneNumberFormatter(weakEditText: WeakReference<EditText>, formatType: PhoneNumberFormatType) : TextWatcher {

    companion object {
        const val MAX_LENGTH_BR = 11
        const val MAX_LENGTH_US = 10
    }

    private val mWeakEditText: WeakReference<EditText> = weakEditText
    private val mFormatType: PhoneNumberFormatType = formatType
    private var maxLENGTH = if (mFormatType == PhoneNumberFormatType.PT_BR) MAX_LENGTH_BR else MAX_LENGTH_US

    private var mFormatting: Boolean = false // this is a flag which prevents the stack(onTextChanged)
    private var mClearFlag: Boolean = false
    private var mLastStartLocation: Int = 0
    private var mLastBeforeText: String? = null

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        if (after == 0 && s.toString() == "(") {
            mClearFlag = true
        }
        mLastStartLocation = start
        mLastBeforeText = s.toString()
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

    override fun afterTextChanged(s: Editable?) {
        // Make sure to ignore calls to afterTextChanged
        // caused by the work done below
        if (!mFormatting) {
            mFormatting = true
            val curPos = mLastStartLocation
            val beforeValue = mLastBeforeText
            val currentValue = s.toString()
            val formattedValue = formatPhoneNumber(s!!)

            if (beforeValue != null) {
                var setCursorPos: Int
                if (currentValue.length > beforeValue.length) {
                    setCursorPos = curPos + (currentValue.length - beforeValue.length)

                    if (formattedValue.length > setCursorPos) {
                        val numbersInsideBrackets = if (mFormatType == PhoneNumberFormatType.EN_US) 3 else 2
                        if (formattedValue[setCursorPos] == ')' && beforeValue.length == numbersInsideBrackets) {
                            setCursorPos += 3
                        } else if (formattedValue[setCursorPos-1] == ')') {
                            setCursorPos += 2
                        } else if (formattedValue[setCursorPos-1] == ' ' || formattedValue[setCursorPos-1] == '(') {
                            setCursorPos += 1
                        }
                    }
                } else {
                    setCursorPos = curPos - (beforeValue.length - currentValue.length) + 1

                    if (setCursorPos < currentValue.length) {
                        while (setCursorPos > 1 && !Character.isDigit(currentValue.get(setCursorPos-1))) {
                            setCursorPos -= 1
                        }
                    }
                }

                mWeakEditText.get()!!.setSelection(if (setCursorPos < 0) 0 else minOf(setCursorPos, formattedValue.length))
            }
            mFormatting = false
        }

    }
    private fun formatPhoneNumber(text: Editable): String {
        val formattedString = StringBuilder()
        // Remove everything except digits
        var p = 0
        while (p < text.length) {
            val ch = text[p]
            if (!Character.isDigit(ch)) {
                text.delete(p, p + 1)
            } else {
                p++
            }
        }

        // Now only digits are remaining
        var allDigitString = text.toString()

        var totalDigitCount = allDigitString.length

        if (totalDigitCount > maxLENGTH) {
            allDigitString = allDigitString.substring(0, maxLENGTH)
            totalDigitCount = allDigitString.length
        }

        if (totalDigitCount == 0
                || totalDigitCount > 11) {
            // May be the total length of input length is greater than the
            // expected value so we'll remove all formatting
            text.clear()
            text.append(allDigitString)
            return allDigitString
        }

        var alreadyPlacedDigitCount = 0
        // Only '(' is remaining and user pressed backspace and so we clear
        // the edit text.
        if (allDigitString == "(" && mClearFlag) {
            text.clear()
            mClearFlag = false
            return ""
        }

        val numbersInsideBrackets = if (mFormatType == PhoneNumberFormatType.EN_US) 3 else 2
        // The first 3 (US) or 2 (BR) numbers beyond ) must be enclosed in brackets "()"
        if (totalDigitCount - alreadyPlacedDigitCount > numbersInsideBrackets) {
            formattedString.append("("
                    + allDigitString.substring(alreadyPlacedDigitCount,
                    alreadyPlacedDigitCount + numbersInsideBrackets) + ") ")
            alreadyPlacedDigitCount += numbersInsideBrackets
        }

        // Check if we are dealing with the new phone format, with an additional digit
        val isNewFormatBR = totalDigitCount == maxLENGTH
        val spaceAfter = if (mFormatType == PhoneNumberFormatType.EN_US) 3 else if (isNewFormatBR) 5 else 4
        // There must be a ' ' inserted after the next 3 (US) or 4/5 (BR) numbers
        if (totalDigitCount - alreadyPlacedDigitCount > spaceAfter) {
            formattedString.append(allDigitString.substring(
                    alreadyPlacedDigitCount, alreadyPlacedDigitCount + spaceAfter) + " ")
            alreadyPlacedDigitCount += spaceAfter
        }

        // All the required formatting is done so we'll just copy the
        // remaining digits.
        if (totalDigitCount > alreadyPlacedDigitCount) {
            formattedString.append(allDigitString
                    .substring(alreadyPlacedDigitCount))
        }

        text.clear()
        text.append(formattedString.toString())
        return formattedString.toString()
    }
}