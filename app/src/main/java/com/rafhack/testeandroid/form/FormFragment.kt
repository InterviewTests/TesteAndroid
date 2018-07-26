package com.rafhack.testeandroid.form

import com.rafhack.testeandroid.R
import com.rafhack.testeandroid.base.BaseProgressFragment

class FormFragment : BaseProgressFragment(), FormContract.View {

    override fun onCreateView(): Int {
        return R.layout.fragment_form
    }

}