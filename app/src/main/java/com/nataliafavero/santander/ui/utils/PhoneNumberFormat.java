package com.nataliafavero.santander.ui.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.lang.ref.WeakReference;

/**
 * Created by nataliafavero on 14/09/18.
 */

public class PhoneNumberFormat implements TextWatcher {

    final int MAX_LENGTH = 11;

    //This TextWatcher sub-class formats entered numbers as (41) 1234(5)?-6789
    private boolean mFormatting; // this is a flag which prevents the stack(onTextChanged)
    private boolean clearFlag;
    private int mLastStartLocation;
    private String mLastBeforeText;
    private WeakReference<EditText> mWeakEditText;

    public PhoneNumberFormat(WeakReference<EditText> weakEditText) {
        this.mWeakEditText = weakEditText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
                                  int after) {
        if (after == 0 && s.toString().equals("(")) {
            clearFlag = true;
        }
        mLastStartLocation = start;
        mLastBeforeText = s.toString();
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before,
                              int count) {
        // TODO: Do nothing
    }

    @Override
    public void afterTextChanged(Editable s) {
        // Make sure to ignore calls to afterTextChanged
        // caused by the work done below
        if (!mFormatting) {
            mFormatting = true;
            int curPos = mLastStartLocation;
            String beforeValue = mLastBeforeText;
            String currentValue = s.toString();
            String formattedValue = formatUsNumber(s);
            if (currentValue.length() > beforeValue.length()) {
                int setCusorPos = formattedValue.length()
                        - (beforeValue.length() - curPos);
                mWeakEditText.get().setSelection(setCusorPos < 0 ? 0 : setCusorPos);
            } else {
                int setCusorPos = formattedValue.length()
                        - (currentValue.length() - curPos);
                if(setCusorPos > 0 && !Character.isDigit(formattedValue.charAt(setCusorPos -1))){
                    setCusorPos--;
                }
                mWeakEditText.get().setSelection(setCusorPos < 0 ? 0 : setCusorPos);
            }
            mFormatting = false;
        }
    }

    private String formatUsNumber(Editable text) {
        StringBuilder formattedString = new StringBuilder();

        // Remove everything except digits
        int p = 0;
        while (p < text.length()) {
            char ch = text.charAt(p);
            if (!Character.isDigit(ch)) {
                text.delete(p, p + 1);
            } else {
                p++;
            }
        }

        // Now only digits are remaining
        String allDigitString = text.toString();

        int totalDigitCount = allDigitString.length();

        if(totalDigitCount > MAX_LENGTH) {
            allDigitString = allDigitString.substring(0, MAX_LENGTH);
            totalDigitCount--;
        }

        // Check if we are dealing with the new phone format, with an additional digit
        boolean isLonger = totalDigitCount == MAX_LENGTH;
        int dashAfter = isLonger ? 5 : 4;

        if (totalDigitCount == 0
                || (totalDigitCount > 11 && !allDigitString.startsWith("("))
                || totalDigitCount > 12) {
            // May be the total length of input length is greater than the
            // expected value so we'll remove all formatting
            text.clear();
            text.append(allDigitString);
            return allDigitString;
        }

        int alreadyPlacedDigitCount = 0;
        // Only ( is remaining and user pressed backspace and so we clear
        // the edit text.
        if (allDigitString.equals("(") && clearFlag) {
            text.clear();
            clearFlag = false;
            return "";
        }

        // The first 2 numbers beyond ) must be enclosed in brackets "()"
        if (totalDigitCount - alreadyPlacedDigitCount > 2) {
            formattedString.append("("
                    + allDigitString.substring(alreadyPlacedDigitCount,
                    alreadyPlacedDigitCount + 2) + ") ");
            alreadyPlacedDigitCount += 2;
        }

        // There must be a '-' inserted after the next 4 or 5 numbers
        // (5 in case we are dealing with the new longer phone format: (xx) xxxxx-xxxx
        if (totalDigitCount - alreadyPlacedDigitCount > dashAfter) {
            formattedString.append(allDigitString.substring(
                    alreadyPlacedDigitCount, alreadyPlacedDigitCount + dashAfter)
                    + "-");
            alreadyPlacedDigitCount += dashAfter;
        }

        // All the required formatting is done so we'll just copy the
        // remaining digits.
        if (totalDigitCount > alreadyPlacedDigitCount) {
            formattedString.append(allDigitString
                    .substring(alreadyPlacedDigitCount));
        }

        text.clear();
        text.append(formattedString.toString());
        return formattedString.toString();
    }
}