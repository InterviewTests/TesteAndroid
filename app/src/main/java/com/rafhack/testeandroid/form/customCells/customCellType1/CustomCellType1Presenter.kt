package com.rafhack.testeandroid.form.customCells.customCellType1

import android.util.Patterns
import java.util.regex.Pattern

class CustomCellType1Presenter(val view: CustomCellType1Contract.View) : CustomCellType1Contract.UserActionListener {

    override fun validatePhoneField(string: String): Boolean {
        val valid = string.isNotEmpty() && Pattern.compile(
                "[(]\\d{2}[)]\\s\\d{4,5}-\\d{4}").matcher(string).matches()
        view.updateErrorState(valid)
        return valid
    }

    override fun validateTextField(string: String): Boolean {
        val valid = string.isNotEmpty()
        view.updateErrorState(valid)
        return valid
    }

    override fun validateEmailField(string: String): Boolean {
        val valid = string.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(string).matches()
        view.updateErrorState(valid)
        return valid
    }

}