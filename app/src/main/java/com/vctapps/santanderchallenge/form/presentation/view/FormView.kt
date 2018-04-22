package com.vctapps.santanderchallenge.form.presentation.view

import com.vctapps.santanderchallenge.core.presentation.BaseView

interface FormView: BaseView {

    fun getFormLayout(): FormLayout

    fun clearForm()

    fun isFormError(): Boolean

    fun showFormErrors()

    fun hideFormErrors()

    fun showSuccessView()

    fun hideSuccessView()

    fun setFormLayoutListeners()

}