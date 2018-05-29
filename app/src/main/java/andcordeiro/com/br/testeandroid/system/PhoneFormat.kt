package andcordeiro.com.br.testeandroid.system

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.lang.ref.WeakReference


class PhoneFormat(weakEditText: WeakReference<EditText>) : TextWatcher {

    private val mWeakEditText: WeakReference<EditText> = weakEditText
    private var maxLENGTH = 11

    private var mFormatting: Boolean = false
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

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(s: Editable?) {
        if (!mFormatting) {
            mFormatting = true
            val curPos = mLastStartLocation
            val beforeValue = mLastBeforeText
            val currentValue = s.toString()
            val formattedValue = formatPhoneNumber(s!!)

            if (beforeValue != null) {
                var setCursorPos: Int = 0
                if (currentValue.length > beforeValue.length) {
                    setCursorPos = curPos + (currentValue.length - beforeValue.length)
                    if (formattedValue.length > setCursorPos) {
                        val numbersInsideBrackets = 2
                        if (formattedValue[setCursorPos] == ')' && beforeValue.length ==
                                numbersInsideBrackets) {
                            setCursorPos += 3
                        } else if (formattedValue[setCursorPos-1] == ')') {
                            setCursorPos += 2
                        } else if (formattedValue[setCursorPos-1] == ' ' ||
                                formattedValue[setCursorPos-1] == '(') {
                            setCursorPos += 1
                        }
                    }
                } else {
                    setCursorPos = curPos - (beforeValue.length - currentValue.length) + 1

                    if (setCursorPos < currentValue.length) {
                        while (setCursorPos > 1 &&
                                !Character.isDigit(currentValue.get(setCursorPos-1))) {
                            setCursorPos -= 1
                        }
                    }
                }
                mWeakEditText.get()!!.setSelection(if (setCursorPos < 0) 0 else minOf(setCursorPos,
                        formattedValue.length))
            }
            mFormatting = false
        }

    }
    private fun formatPhoneNumber(text: Editable): String {
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

        if (totalDigitCount > maxLENGTH) {
            allDigitString = allDigitString.substring(0, maxLENGTH)
            totalDigitCount = allDigitString.length
        }

        if (totalDigitCount == 0
                || totalDigitCount > 11) {
            text.clear()
            text.append(allDigitString)
            return allDigitString
        }

        var alreadyPlacedDigitCount = 0

        if (allDigitString == "(" && mClearFlag) {
            text.clear()
            mClearFlag = false
            return ""
        }

        if (totalDigitCount - alreadyPlacedDigitCount > 2) {
            formattedString.append("("
                    + allDigitString.substring(alreadyPlacedDigitCount,
                    alreadyPlacedDigitCount + 2) + ") ")
            alreadyPlacedDigitCount += 2
        }

        val isNewFormatBR = totalDigitCount == maxLENGTH
        val spaceAfter = if (isNewFormatBR) 5 else 4
        if (totalDigitCount - alreadyPlacedDigitCount > spaceAfter) {
            formattedString.append(allDigitString.substring(
                    alreadyPlacedDigitCount, alreadyPlacedDigitCount + spaceAfter) + " ")
            alreadyPlacedDigitCount += spaceAfter
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