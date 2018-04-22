package com.vctapps.santanderchallenge.form.presentation.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.vctapps.santanderchallenge.R
import com.vctapps.santanderchallenge.form.presentation.presenter.FormPresenter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_form.*
import javax.inject.Inject

class FormActivity : AppCompatActivity(), FormView {

    @Inject
    lateinit var presenter: FormPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_form)

        presenter.onStart()

    }

    override fun getFormLayout(): FormLayout {
        return formRootView
    }

    override fun isFormError(): Boolean {
        return false
    }

    override fun showFormErrors() {

    }

    override fun hideFormErrors() {

    }

    override fun showSuccessView() {

    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showError() {

    }

    override fun hideError() {

    }
}
