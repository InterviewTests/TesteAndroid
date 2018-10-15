package com.rafhack.testeandroid.form.customCells.customCellType1

import com.rafhack.testeandroid.base.BaseContract

interface CustomCellType1Contract {

    interface View : BaseContract.View {
        fun updateErrorState(valid: Boolean)
    }

    interface UserActionListener : BaseContract.Presenter<View> {
        fun validatePhoneField(string: String): Boolean
        fun validateTextField(string: String): Boolean
        fun validateEmailField(string: String): Boolean
    }

}