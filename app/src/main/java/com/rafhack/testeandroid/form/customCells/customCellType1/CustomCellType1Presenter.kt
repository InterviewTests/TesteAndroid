package com.rafhack.testeandroid.form.customCells.customCellType1

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
        val valid = string.isNotEmpty() && Pattern.compile(
                "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                        "\\@" +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                        "(" +
                            "\\." +
                            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                        ")+").matcher(string).matches()
        view.updateErrorState(valid)
        return valid
    }

}