package com.study.vipoliveira.investapp.ui.contact

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import com.study.vipoliveira.investapp.R
import com.study.vipoliveira.investapp.data.network.contact.entities.ContactFormResponse
import com.study.vipoliveira.investapp.util.PhoneNumberFormatType
import com.study.vipoliveira.investapp.util.PhoneNumberFormatter
import com.study.vipoliveira.investapp.util.toast
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_contact.*
import kotlinx.android.synthetic.main.fragment_success.*
import kotlinx.android.synthetic.main.layout_network_state.*
import java.lang.ref.WeakReference
import java.util.ArrayList
import javax.inject.Inject

class ContactFormFragment: Fragment(), ContactFormContract.View, View.OnClickListener{
    private val views = ArrayList<View>()

    override fun onClick(view: View?) {
        if(presenter.validateInput(views)){
            sendInfo()
        }
    }

    private fun sendInfo() {
        successView.visibility = View.VISIBLE
        scrollView.visibility = View.GONE
        setUpBackForm()
    }

    private fun setUpBackForm() {
        backToForm.setOnClickListener{
            backToContactForm()
        }
    }

    private fun backToContactForm() {
        successView.visibility = View.GONE
        scrollView.visibility = View.VISIBLE
        presenter.getContactForm()
    }

    @Inject
    lateinit var presenter: ContactFormContract.Presenter

    private var root: View? = null

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_contact, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        presenter.getContactForm()
    }

    override fun displayLoadingUI() {
        loading_progress_bar.visibility = View.VISIBLE
        retryButtonSetUp()
    }

    private fun retryButtonSetUp() {
        retry_button.visibility = View.GONE
        retry_button.setOnClickListener{presenter.getContactForm()}
    }

    private fun displayRetryButton(){
        retry_button.visibility = View.VISIBLE
    }

    override fun hideLoadingUI() {
        loading_progress_bar.visibility = View.GONE
    }

    override fun displayError(error: String) {
        error_msg_txt.visibility = View.VISIBLE
        error_msg_txt.text = error
        hideLoadingUI()
        displayRetryButton()
    }

    private fun hideError(){
        error_msg_txt.visibility = View.GONE
    }

    override fun createContactForm(contact: ContactFormResponse) {
        hideError()
        contactForm.removeAllViews()
        contact.cells.forEach {
            cell ->
            when (cell.type){
                1 -> {
                    val editText = layoutInflater.inflate(R.layout.edit_text_custom,
                        contactForm, false) as EditText
                    editText.tag = cell.id
                    editText.hint = cell.message
                    if (cell.hidden) editText.visibility = View.GONE else editText.visibility = View.VISIBLE
                    when (cell.typeField) {
                        "1" -> {
                            editText.inputType = InputType.TYPE_CLASS_TEXT
                        }

                        "telnumber" -> {
                            editText.inputType = InputType.TYPE_CLASS_PHONE

                            val country = PhoneNumberFormatType.PT_BR
                            editText.addTextChangedListener(PhoneNumberFormatter(WeakReference(editText), country))
                        }
                        "3" -> {
                            editText.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                        }
                    }
                    views.add(editText)
                    contactForm.addView(editText)
                }
                2 -> {
                    val textView = layoutInflater.inflate(R.layout.text_view_custom,
                            contactForm, false) as TextView
                    textView.tag = cell.id
                    textView.text = cell.message
                    views.add(textView)
                    contactForm.addView(textView)
                }

                3 -> {
                    //No Image
                }
                4 -> {
                    val checkBox = layoutInflater.inflate(R.layout.check_box_custom,
                        contactForm, false) as CheckBox
                    checkBox.tag = cell.id
                    checkBox.text = cell.message
                    checkBox.setOnClickListener(showHiddenFields)
                    views.add(checkBox)
                    contactForm.addView(checkBox)
                }
                5 -> {
                    val button = layoutInflater.inflate(R.layout.button_custom,
                            contactForm, false) as Button
                    button.tag = cell.id
                    button.text = cell.message
                    button.setOnClickListener(this)
                    views.add(button)
                    contactForm.addView(button)
                }
            }

        }
    }

    private val showHiddenFields = View.OnClickListener {
        for (component in views) {
            if (component is EditText) {
                if (component.inputType == InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS){
                    if (component.isShown()) {
                        component.setVisibility(View.GONE)
                    } else {
                        component.setVisibility(View.VISIBLE)
                    }
                }
            }

        }
    }

    override fun showEmailError() {
        context?.toast(getString(R.string.email_error))
    }

    override fun showPhoneError() {
        context?.toast(getString(R.string.phone_error))

    }

    override fun showTextError() {
        context?.toast(getString(R.string.text_error))
    }

    override fun onDestroy() {
        presenter.clearDiposable()
        super.onDestroy()
    }
}