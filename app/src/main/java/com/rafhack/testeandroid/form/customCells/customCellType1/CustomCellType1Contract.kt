package com.rafhack.testeandroid.form.customCells.customCellType1

interface CustomCellType1Contract {

    interface View {
        fun updateErrorState(valid: Boolean)
    }

    interface UserActionListener {
        fun validatePhoneField(string: String): Boolean
        fun validateTextField(string: String): Boolean
        fun validateEmailField(string: String): Boolean
    }

}