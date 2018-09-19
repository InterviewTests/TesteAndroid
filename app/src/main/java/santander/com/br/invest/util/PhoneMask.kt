package santander.com.br.invest.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.lang.ref.WeakReference


/**
 * Code from https://gist.github.com/alfredbaudisch/f4416061dd1858b1f4ee
 */
class PhoneMask(private val mWeakEditText: WeakReference<EditText>) : TextWatcher {

  private val MAX_LENGTH = 11

  //This TextWatcher sub-class formats entered numbers as (41) 1234(5)?-6789
  private var mFormatting: Boolean = false // this is a flag which prevents the stack(onTextChanged)
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

  override fun onTextChanged(s: CharSequence, start: Int, before: Int,
                             count: Int) {
  }

  override fun afterTextChanged(s: Editable) {
    // Make sure to ignore calls to afterTextChanged
    // caused by the work done below
    if (!mFormatting) {
      mFormatting = true
      val curPos = mLastStartLocation
      val beforeValue = mLastBeforeText
      val currentValue = s.toString()
      val formattedValue = formatUsNumber(s)
      if (currentValue.length > beforeValue!!.length) {
        val setCusorPos = formattedValue.length - (beforeValue.length - curPos)
        mWeakEditText.get()?.setSelection(if (setCusorPos < 0) 0 else setCusorPos)
      } else {
        var setCusorPos = formattedValue.length - (currentValue.length - curPos)
        if (setCusorPos > 0 && !Character.isDigit(formattedValue[setCusorPos - 1])) {
          setCusorPos--
        }
        mWeakEditText.get()?.setSelection(if (setCusorPos < 0) 0 else setCusorPos)
      }
      mFormatting = false
    }
  }

  private fun formatUsNumber(text: Editable): String {
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

    if (totalDigitCount > MAX_LENGTH) {
      allDigitString = allDigitString.substring(0, MAX_LENGTH)
      totalDigitCount--
    }

    // Check if we are dealing with the new phone format, with an additional digit
    val isLonger = totalDigitCount == MAX_LENGTH
    val dashAfter = if (isLonger) 5 else 4

    if (totalDigitCount == 0
        || totalDigitCount > 11 && !allDigitString.startsWith("(")
        || totalDigitCount > 12) {
      // May be the total length of input length is greater than the
      // expected value so we'll remove all formatting
      text.clear()
      text.append(allDigitString)
      return allDigitString
    }

    var alreadyPlacedDigitCount = 0
    // Only ( is remaining and user pressed backspace and so we clear
    // the edit text.
    if (allDigitString == "(" && clearFlag) {
      text.clear()
      clearFlag = false
      return ""
    }

    // The first 2 numbers beyond ) must be enclosed in brackets "()"
    if (totalDigitCount - alreadyPlacedDigitCount > 2) {
      formattedString.append("("
          + allDigitString.substring(alreadyPlacedDigitCount,
          alreadyPlacedDigitCount + 2) + ") ")
      alreadyPlacedDigitCount += 2
    }

    // There must be a '-' inserted after the next 4 or 5 numbers
    // (5 in case we are dealing with the new longer phone format: (xx) xxxxx-xxxx
    if (totalDigitCount - alreadyPlacedDigitCount > dashAfter) {
      formattedString.append(allDigitString.substring(
          alreadyPlacedDigitCount, alreadyPlacedDigitCount + dashAfter) + "-")
      alreadyPlacedDigitCount += dashAfter
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
