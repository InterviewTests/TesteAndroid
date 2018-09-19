package santander.com.br.invest.ui.fragments

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.AppCompatCheckBox
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_contact.*
import kotlinx.android.synthetic.main.fragment_error.*
import kotlinx.android.synthetic.main.fragment_success.*
import santander.com.br.invest.R
import santander.com.br.invest.contract.ContactContract
import santander.com.br.invest.di.component.DaggerContactComponent
import santander.com.br.invest.extension.gone
import santander.com.br.invest.extension.visible
import santander.com.br.invest.model.Cell
import santander.com.br.invest.model.Type
import santander.com.br.invest.model.TypeField
import santander.com.br.invest.util.PhoneMask
import java.lang.ref.WeakReference
import javax.inject.Inject

class ContactFragment : Fragment(), ContactContract.View {

  @Inject
  lateinit var presenter: ContactContract.Presenter

  private var name: String? = ""
  private var phone: String? = ""
  private var email: String? = ""
  private var registerEmail: Boolean = false

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {

    initDagger()

    return inflater.inflate(R.layout.fragment_contact, container, false)


  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initView(savedInstanceState)
    initListeners()
  }

  override fun showContactLayout() {
    contactForm.visible()
  }

  override fun hideContactLayout() {
    contactForm.gone()
  }

  override fun showErrorView() {
    viewError.visible()
  }

  override fun hideErrorView() {
    viewError.gone()
  }

  override fun showLoading() {
    progressBar.visible()
  }

  override fun hideLoading() {
    progressBar.gone()
  }

  override fun createContactForm(cellList: ArrayList<Cell>) {
    contactForm.removeAllViews()

    name = ""
    phone = ""
    email = ""
    registerEmail = false

    var params: ConstraintLayout.LayoutParams
    var constraintSet: ConstraintSet
    cellList.forEach { cell ->
      when (cell.type) {
        Type.FIELD -> {
          val contextThemeWrapper = ContextThemeWrapper(context, R.style.TextField)
          val textInputLayout = TextInputLayout(contextThemeWrapper)
          textInputLayout.tag = cell
          textInputLayout.isErrorEnabled = true
          textInputLayout.id = cell.id
          context?.let { context ->
            textInputLayout.typeface = ResourcesCompat.getFont(context, R.font.dinpro_light)
          }
          textInputLayout.orientation = LinearLayout.HORIZONTAL
          textInputLayout.setHintTextAppearance(R.style.TextFieldHint)
          textInputLayout.setErrorTextAppearance(R.style.TextFieldError)
          if (cell.hidden) {
            textInputLayout.gone()
          } else {
            textInputLayout.visible()
          }
          val textInputEditText = TextInputEditText(contextThemeWrapper)
          context?.let { context ->
            textInputEditText.setTextColor(ContextCompat.getColor(context, R.color.darkGrey))
            textInputEditText.setHintTextColor(ContextCompat.getColor(context, R.color.darkGrey))
          }
          textInputEditText.hint = cell.message

          when (cell.typeField) {
            TypeField.TEXT -> {
              textInputEditText.inputType = InputType.TYPE_CLASS_TEXT
              textInputEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                  name = s.toString()
                }

                override fun afterTextChanged(s: Editable) {
                  textInputLayout.error = null
                }
              })
            }
            TypeField.TEL_NUMBER -> {
              textInputEditText.inputType = InputType.TYPE_CLASS_PHONE
              textInputEditText.addTextChangedListener(PhoneMask(WeakReference(textInputEditText)))
              textInputEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                  phone = s.toString()
                }

                override fun afterTextChanged(s: Editable) {
                  textInputLayout.error = null
                }
              })
            }
            else -> {
              textInputEditText.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
              textInputEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                  email = s.toString()
                }

                override fun afterTextChanged(s: Editable) {
                  textInputLayout.error = null
                }
              })
            }
          }

          textInputLayout.addView(textInputEditText, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
              LinearLayout.LayoutParams.WRAP_CONTENT))
          textInputLayout.requestLayout()

          params = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT)
          params.topToBottom = contactForm.getChildAt(contactForm.childCount - 1).id
          params.topMargin = cell.topSpacing.toInt()
          contactForm.addView(textInputLayout, params)

          constraintSet = ConstraintSet()
          constraintSet.clone(contactForm)
          constraintSet.connect(textInputLayout.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
          constraintSet.connect(textInputLayout.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
          constraintSet.applyTo(contactForm)

        }
        Type.TEXT -> {
          val textView = TextView(context)
          textView.tag = cell
          textView.id = cell.id
          textView.text = cell.message.capitalize()
          if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            @Suppress("DEPRECATION")
            textView.setTextAppearance(context, R.style.TextFieldLabel)
          } else {
            textView.setTextAppearance(R.style.TextFieldLabel)
          }
          params = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT,
              ConstraintLayout.LayoutParams.WRAP_CONTENT)
          params.topMargin = cell.topSpacing.toInt()
          contactForm.addView(textView, params)
        }
        Type.CHECK_BOX -> {
          val checkBox = AppCompatCheckBox(ContextThemeWrapper(context, R.style.CheckBoxForm))
          checkBox.tag = cell
          checkBox.id = cell.id
          if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            @Suppress("DEPRECATION")
            checkBox.setTextAppearance(context, R.style.TextFieldLabel)
          } else {
            checkBox.setTextAppearance(R.style.TextFieldLabel)
          }
          checkBox.text = cell.message
          checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            val cellTag: Cell = buttonView.tag as Cell
            cellTag.show?.let { show ->
              registerEmail = isChecked
              contactForm.findViewById<View>(show).visibility = (if (isChecked) View.VISIBLE else View.GONE)
            }
          }

          params = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT)
          params.topToBottom = contactForm.getChildAt(contactForm.childCount - 1).id
          params.topMargin = cell.topSpacing.toInt()
          contactForm.addView(checkBox, params)
        }
        Type.SEND -> {
          val button = Button(ContextThemeWrapper(context, R.style.ButtonForm))
          button.tag = cell
          button.typeface = ResourcesCompat.getFont(context!!, R.font.dinpro_light)
          button.setBackgroundResource(R.drawable.button_background)
          button.id = cell.id
          button.text = cell.message
          button.setTextColor(Color.WHITE)
          button.setOnClickListener { presenter.sendContact(name, phone, email, registerEmail) }

          params = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT)
          params.topToBottom = contactForm.getChildAt(contactForm.childCount - 1).id
          params.topMargin = cell.topSpacing.toInt()
          contactForm.addView(button, params)

          constraintSet = ConstraintSet()
          constraintSet.clone(contactForm)
          constraintSet.connect(button.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
          constraintSet.applyTo(contactForm)
        }
        else -> {

        }
      }
    }
  }

  override fun showNameErrorMessage() {
    val textInputLayout: TextInputLayout? = getFieldByType(TypeField.TEXT)
    textInputLayout?.editText?.error = getString(R.string.required_field)
  }

  override fun showEmailErrorMEssage() {
    val textInputLayout: TextInputLayout? = getFieldByType(TypeField.EMAIL)
    textInputLayout?.editText?.error = getString(R.string.invalid_email)
  }

  override fun showPhoneErrorMessage() {
    val textInputLayout: TextInputLayout? = getFieldByType(TypeField.TEL_NUMBER)
    textInputLayout?.editText?.error = getString(R.string.invalid_phone)
  }

  override fun showMessageSendSuccessful() {
    viewSuccess.visible()
  }

  override fun hideMessageSendSuccessful() {
    viewSuccess.gone()
  }

  private fun initView(savedInstanceState: Bundle?) {
    presenter.bindView(this)
    presenter.onCreate(savedInstanceState)
  }

  private fun initDagger() {
    DaggerContactComponent.builder().build().inject(this@ContactFragment)
  }

  private fun initListeners() {
    buttonNewMessage.setOnClickListener {
      presenter.showFormsAgain()
    }

    tryAgain.setOnClickListener {
      presenter.getContact()
    }
  }

  private fun getFieldByType(type: TypeField): TextInputLayout? {
    for (i in 0 until contactForm.childCount) {
      val view = contactForm.getChildAt(i)
      val cell = view.tag as Cell
      if (cell.type == Type.FIELD && cell.typeField == type) {
        return view as TextInputLayout
      }
    }
    return null
  }
}

