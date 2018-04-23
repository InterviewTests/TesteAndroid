package com.vctapps.santanderchallenge.form.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vctapps.santanderchallenge.R
import com.vctapps.santanderchallenge.core.presentation.BaseFragment
import com.vctapps.santanderchallenge.form.presentation.presenter.FormPresenter
import kotlinx.android.synthetic.main.fragment_form.*
import kotlinx.android.synthetic.main.success_form.*
import javax.inject.Inject

class FormFragment : BaseFragment(), FormView {

    @Inject
    lateinit var presenter: FormPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_form, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setUpNewMessageRequest()

        presenter.onStart()
    }

    private fun setUpNewMessageRequest() {
        textViewSendNewMessage.setOnClickListener { presenter.onSendNewMessageClicked() }
    }

    override fun getFormLayout(): FormLayout {
        return formRootView
    }

    override fun isFormError(): Boolean {
        return formRootView.checkErrors()
    }

    override fun showFormErrors() {
        hideFormErrors()
        formRootView.showErrors()
    }

    override fun hideFormErrors() {
        formRootView.hideErrors()
    }

    override fun showSuccessView() {
        successViewLayout.visibility = View.VISIBLE
    }

    override fun hideSuccessView() {
        successViewLayout.visibility = View.GONE
    }

    override fun clearForm() {
        formRootView.clearFields()
    }

    override fun setFormLayoutListeners() {
        formRootView.setSendButtonListener(View.OnClickListener {
            presenter.onSendEventClicked()
        })
    }
}
