package br.com.gservices.santanderteste.pages.contacts.presenter

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
import br.com.gservices.santanderteste.R
import br.com.gservices.santanderteste.core.presenter.BaseFragment
import br.com.gservices.santanderteste.core.types.CommonTypes
import br.com.gservices.santanderteste.core.types.FieldTypes
import br.com.gservices.santanderteste.pages.contacts.data.entities.Contacts
import br.com.gservices.santanderteste.pages.contacts.interfaces.ContractContactsInterface
import br.com.gservices.santanderteste.utils.StringUtils
import br.com.gservices.santanderteste.utils.ValidationUtils
import br.com.gservices.santanderteste.utils.showSnack
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.fragment_contact.*
import org.koin.android.ext.android.inject

class ContactFragment : BaseFragment(), ContractContactsInterface.View, SwipeRefreshLayout.OnRefreshListener {
    override fun clearForm() {
        contactForm.removeAllViews()
        successForm.visibility = View.VISIBLE
    }

    private var contactList: List<Contacts>? = null

    override fun onRefresh() {
        presenter.loadContact()
    }

    override fun showMessage(message: String) {
        view?.showSnack(message)
    }

    override fun showLoading(active: Boolean) {
        swiperefresh.isRefreshing = active
    }

    override val presenter: ContractContactsInterface.Presenter by inject()

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

    override fun showSuccess(contact: List<Contacts>) {
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
                CommonTypes.FIELD -> {
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
                        FieldTypes.TEXT -> {
                            textInputEditText.inputType = InputType.TYPE_CLASS_TEXT
                            if(contact.required!!)
                                contact.errMsg = getString(R.string.text_null)
                            textInputEditText.addTextChangedListener(object : TextWatcher {
                                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                                    contact.errMsg = ""
                                }

                                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                                    contact.expected = s.toString()
                                    if (ValidationUtils.isInvalidName(contact.expected)){
                                        contact.errMsg = getString(R.string.text_invalid)
                                        (typeComponent as TextInputLayout).isErrorEnabled = true
                                        (typeComponent as TextInputLayout).error = contact.errMsg
                                    }else {
                                        (typeComponent as TextInputLayout).error = null
                                        contact.errMsg = ""
                                    }
                                    presenter.updateObjectList(contact)
                                }

                                override fun afterTextChanged(s: Editable) {

                                }
                            })
                        }


                        FieldTypes.TELNUMBER -> {
                            textInputEditText.inputType = InputType.TYPE_CLASS_PHONE
                            if(contact.required!!)
                                contact.errMsg = getString(R.string.phone_null)
                            textInputEditText.addTextChangedListener(StringUtils.adicionaMascaraTelefoneCadastro(textInputEditText))
                            textInputEditText.addTextChangedListener(object : TextWatcher {
                                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                                    contact.errMsg = ""
                                }

                                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                                    contact.expected = s.toString()
                                    if (!ValidationUtils.isPhoneInvalid(StringUtils.unmaskString(contact.expected))){
                                        contact.errMsg = getString(R.string.phone_invalid)
                                        (typeComponent as TextInputLayout).isErrorEnabled = true
                                        (typeComponent as TextInputLayout).error = contact.errMsg

                                    }else {
                                        (typeComponent as TextInputLayout).error = null
                                        (typeComponent as TextInputLayout).isErrorEnabled = false
                                        contact.errMsg = ""
                                    }
                                    presenter.updateObjectList(contact)
                                }

                                override fun afterTextChanged(s: Editable) {
                                }
                            })
                        }

                        FieldTypes.EMAIL -> {
                            textInputEditText.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                            if(contact.required!!)
                                contact.errMsg = getString(R.string.email_null)
                            textInputEditText.addTextChangedListener(object : TextWatcher {
                                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                                    contact.errMsg = ""
                                }

                                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                                    contact.expected = s.toString()
                                    if (!ValidationUtils.isValidEmail(contact.expected)){
                                        contact.errMsg = getString(R.string.email_invalid)
                                        (typeComponent as TextInputLayout).isErrorEnabled = true
                                        (typeComponent as TextInputLayout).error = contact.errMsg
                                    }else {
                                        (typeComponent as TextInputLayout).error = null
                                        (typeComponent as TextInputLayout).isErrorEnabled =false
                                        contact.errMsg = ""
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
                CommonTypes.TEXT -> {
                    typeComponent = TextView(context)
                    typeComponent.text = contact.message
                    typeComponent.setTextAppearance(context, R.style.LabelCustom)
                }
                CommonTypes.CHECKBOX -> {
                    typeComponent = AppCompatCheckBox(ContextThemeWrapper(context, R.style.CheckBoxCustom))
                    typeComponent.setTextAppearance(context, R.style.LabelCustom)
                    typeComponent.text = contact.message
                    typeComponent.setOnCheckedChangeListener { buttonView, isChecked ->
                        val cellTag: Contacts = buttonView.tag as Contacts
                        cellTag.show?.let { show ->
                            contactForm.findViewById<View>(show).visibility = (
                                    if (isChecked) View.VISIBLE else View.GONE)
                        }

                        contact.requiredCheck = isChecked
                        presenter.updateObjectListHidden(contact)
                    }
                }
                CommonTypes.SEND -> {
                    contact.expected = "true"
                    typeComponent = Button(ContextThemeWrapper(context, R.style.ButtonCustom))
                    typeComponent.setBackgroundResource(R.drawable.button_background)
                    typeComponent.text = contact.message
                    contact.errMsg = ""
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