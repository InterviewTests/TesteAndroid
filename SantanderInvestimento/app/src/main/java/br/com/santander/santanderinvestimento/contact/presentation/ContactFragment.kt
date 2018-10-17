package br.com.santander.santanderinvestimento.contact.presentation

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.view.ContextThemeWrapper
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import br.com.santander.santanderinvestimento.R
import br.com.santander.santanderinvestimento.contact.data.entity.Contact
import br.com.santander.santanderinvestimento.contact.domain.Type
import br.com.santander.santanderinvestimento.contact.domain.TypeField
import br.com.santander.santanderinvestimento.core.presentation.BaseFragment
import br.com.santander.santanderinvestimento.util.StringHelper
import br.com.santander.santanderinvestimento.util.ValidationUtil
import br.com.santander.santanderinvestimento.util.showSnack
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.fragment_contact.*
import org.koin.android.ext.android.inject

class ContactFragment : BaseFragment(), ContactContract.View, SwipeRefreshLayout.OnRefreshListener {
    override fun clearForm() {
        contactForm.removeAllViews()
        successForm.visibility = View.VISIBLE
    }

    private var contactList: List<Contact>? = null

    override fun onRefresh() {
        presenter.loadContact()
    }

    override fun showMessage(message: String) {
        view?.showSnack(message)
    }

    override fun showLoading(active: Boolean) {
        swiperefresh.isRefreshing = active
    }

    override val presenter: ContactContract.Presenter by inject()

    companion object {
        fun newInstance() = ContactFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_contact, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareSwipeRefreshLayout()
        txtNewMessage.setOnClickListener { onRefresh() }
    }

    private fun prepareSwipeRefreshLayout() {
        swiperefresh?.setColorSchemeColors(Color.RED)
        swiperefresh?.setOnRefreshListener(this)
    }


    override fun onResume() {
        super.onResume()
        presenter.subscribe(this)
        presenter.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unSubscribe()
    }

    override fun showSuccess(contact: List<Contact>) {
        contactForm.removeAllViews()
        successForm.visibility = View.INVISIBLE
        layoutMain.visibility = View.VISIBLE
        this.contactList = contact
        presenter.setContactList(contact)

        var params: ConstraintLayout.LayoutParams
        var constraintSet: ConstraintSet
        contactList?.forEach { contact ->
            var typeComponent: View? = null

            when (contact.type) {
                Type.FIELD -> {
                    val contextThemeWrapper = ContextThemeWrapper(context, R.style.TextField)
                    typeComponent = TextInputLayout(contextThemeWrapper)
                    typeComponent.orientation = LinearLayout.HORIZONTAL
                    val textInputEditText = TextInputEditText(contextThemeWrapper)
                    context?.let { context ->
                        textInputEditText.setTextColor(ContextCompat.getColor(context, R.color.gray7e7e))
                        textInputEditText.setHintTextColor(ContextCompat.getColor(context, R.color.gray7e7e))
                    }
                    textInputEditText.hint = contact.message

                    when (contact.typeField) {
                        TypeField.TEXT -> {
                            textInputEditText.inputType = InputType.TYPE_CLASS_TEXT
                            if(contact.required!!)
                                contact.messageError = getString(R.string.text_null)
                            textInputEditText.addTextChangedListener(object : TextWatcher {
                                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                                    contact.messageError = ""
                                }

                                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                                    contact.userExpected = s.toString()
                                    if (ValidationUtil.isInvalidName(contact.userExpected)){
                                        contact.messageError = getString(R.string.text_invalid)
                                        (typeComponent as TextInputLayout).isErrorEnabled = true
                                        (typeComponent as TextInputLayout).error = contact.messageError
                                    }else {
                                        (typeComponent as TextInputLayout).error = null
                                        contact.messageError = ""
                                    }
                                    presenter.updateObjectList(contact)
                                }

                                override fun afterTextChanged(s: Editable) {

                                }
                            })
                        }


                        TypeField.TELNUMBER -> {
                            textInputEditText.inputType = InputType.TYPE_CLASS_PHONE
                            if(contact.required!!)
                                contact.messageError = getString(R.string.phone_null)
                            textInputEditText.addTextChangedListener(StringHelper.adicionaMascaraTelefoneCadastro(textInputEditText))
                            textInputEditText.addTextChangedListener(object : TextWatcher {
                                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                                    contact.messageError = ""
                                }

                                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                                    contact.userExpected = s.toString()
                                    if (!ValidationUtil.isPhoneInvalid(StringHelper.unmaskString(contact.userExpected))){
                                        contact.messageError = getString(R.string.phone_invalid)
                                        (typeComponent as TextInputLayout).isErrorEnabled = true
                                        (typeComponent as TextInputLayout).error = contact.messageError

                                    }else {
                                        (typeComponent as TextInputLayout).error = null
                                        (typeComponent as TextInputLayout).isErrorEnabled = false
                                        contact.messageError = ""
                                    }
                                    presenter.updateObjectList(contact)
                                }

                                override fun afterTextChanged(s: Editable) {
                                }
                            })
                        }

                        TypeField.EMAIL -> {
                            textInputEditText.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                            if(contact.required!!)
                                contact.messageError = getString(R.string.email_null)
                            textInputEditText.addTextChangedListener(object : TextWatcher {
                                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                                    contact.messageError = ""
                                }

                                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                                    contact.userExpected = s.toString()
                                    if (!ValidationUtil.isValidEmail(contact.userExpected)){
                                        contact.messageError = getString(R.string.email_invalid)
                                        (typeComponent as TextInputLayout).isErrorEnabled = true
                                        (typeComponent as TextInputLayout).error = contact.messageError
                                    }else {
                                        (typeComponent as TextInputLayout).error = null
                                        (typeComponent as TextInputLayout).isErrorEnabled =false
                                        contact.messageError = ""
                                    }
                                    presenter.updateObjectList(contact)
                                }

                                override fun afterTextChanged(s: Editable) {

                                }
                            })
                        }
                    }

                    typeComponent.addView(textInputEditText, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT))
                    typeComponent.requestLayout()
                }
                Type.TEXT -> {
                    typeComponent = TextView(context)
                    typeComponent.text = contact.message
                    typeComponent.setTextAppearance(context, R.style.LabelCustom)
                }
                Type.CHECKBOX -> {
                    typeComponent = AppCompatCheckBox(ContextThemeWrapper(context, R.style.CheckBoxCustom))
                    typeComponent.setTextAppearance(context, R.style.LabelCustom)
                    typeComponent.text = contact.message
                    typeComponent.setOnCheckedChangeListener { buttonView, isChecked ->
                        val cellTag: Contact = buttonView.tag as Contact
                        cellTag.show?.let { show ->
                            contactForm.findViewById<View>(show).visibility = (
                                    if (isChecked) View.VISIBLE else View.GONE)
                        }

                        contact.requireValidateCheck = isChecked
                        presenter.updateObjectListHidden(contact)
                    }
                }
                Type.SEND -> {
                    contact.userExpected = "true"
                    typeComponent = Button(ContextThemeWrapper(context, R.style.ButtonCustom))
                    typeComponent.setBackgroundResource(R.drawable.button_background)
                    typeComponent.text = contact.message
                    contact.messageError = ""
                    typeComponent.setOnClickListener {
                        presenter.sendContact()
                    }
                }
                else -> {
                }
            }

            typeComponent?.tag = contact
            typeComponent?.id = contact.id!!

            if (contact.hidden!!) {
                typeComponent?.visibility = View.GONE
            } else {
                typeComponent?.visibility = View.VISIBLE
            }
            params = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT)
            if (contactForm.getChildAt(contactForm.childCount - 1) != null)
                params.topToBottom = contactForm.getChildAt(contactForm.childCount - 1).id
            params.topMargin = ((contact.topSpacing!! * resources.displayMetrics.density).toInt())
            contactForm.addView(typeComponent, params)
            constraintSet = ConstraintSet()
            constraintSet.clone(contactForm)
            constraintSet.connect(typeComponent!!.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
            constraintSet.applyTo(contactForm)
        }
    }


}