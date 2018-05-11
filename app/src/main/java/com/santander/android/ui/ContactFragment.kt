package com.santander.android.ui

import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.InputType
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Patterns
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.santander.android.R
import com.santander.android.event.SendContactEvent
import com.santander.android.model.template.ContactsTemplate
import com.santander.android.viewmodel.ContactViewModel
import org.greenrobot.eventbus.EventBus

class ContactFragment : Fragment() {

    private lateinit var mViewModel: ContactViewModel
    private var mContactsTemplate = ContactsTemplate()
    private val mForm = HashMap<Int, String>()

    // Views
    private lateinit var mDynamicForm: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let { mViewModel = ContactViewModel.create(it) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_contact, container, false)
        loadViews(rootView)
        loadObservers()
        return rootView
    }

    private fun loadViews(rootView: View) {
        mDynamicForm = rootView.findViewById(R.id.fragment_contact_dynamic_form)
    }

    private fun loadValues() {
        for (cell in mContactsTemplate.cells) {
            when (ContactsTemplate.Type.from(cell.type)) {
                ContactsTemplate.Type.Text -> loadText(cell)
                ContactsTemplate.Type.Field -> loadField(cell)
                ContactsTemplate.Type.Image -> loadImage(cell)
                ContactsTemplate.Type.Checkbox -> loadCheckbox(cell)
                ContactsTemplate.Type.Send -> loadSend(cell)
            }
        }
    }

    private fun loadText(template: ContactsTemplate.ContactTemplate) {
        with(template) {
            val text = TextView(activity)
            text.text = message
            text.visibility = if (hidden) View.GONE else View.VISIBLE
            mDynamicForm.addView(text)
            setTopSpacing(text, topSpacing)
        }
    }

    private fun loadField(template: ContactsTemplate.ContactTemplate) {
        with(template) {
            val field = EditText(activity)
            field.hint = message
            val typeFieldString = ContactsTemplate.TypeField.from(typefield)
            when (typeFieldString) {
                ContactsTemplate.TypeField.Text -> field.inputType = InputType.TYPE_TEXT_VARIATION_NORMAL
                ContactsTemplate.TypeField.TelNumber -> field.inputType = InputType.TYPE_CLASS_PHONE
                ContactsTemplate.TypeField.Email -> field.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
            }
            if (typeFieldString == ContactsTemplate.TypeField.TelNumber) maskPhone(field)
            field.visibility = if (hidden) View.GONE else View.VISIBLE
            mDynamicForm.addView(field)
            setTopSpacing(field, topSpacing)
        }
    }

    private fun loadImage(template: ContactsTemplate.ContactTemplate) {
        with(template) {
            val image = ImageView(activity)
            image.visibility = if (hidden) View.GONE else View.VISIBLE
            mDynamicForm.addView(image)
            setTopSpacing(image, topSpacing)
        }
    }

    private fun loadCheckbox(template: ContactsTemplate.ContactTemplate) {
        with(template) {
            val checkBox = CheckBox(activity)
            checkBox.text = message
            checkBox.visibility = if (hidden) View.GONE else View.VISIBLE
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                val toShow = searchView(show)
                if (isChecked && toShow != null) toShow.visibility = View.VISIBLE
                else if (!isChecked && toShow != null) toShow.visibility = View.GONE
            }
            mDynamicForm.addView(checkBox)
            setTopSpacing(checkBox, topSpacing)
        }
    }

    private fun loadSend(template: ContactsTemplate.ContactTemplate) {
        with(template) {
            val sendButton = TextView(activity)
            sendButton.isClickable = true
            sendButton.text = message
            sendButton.visibility = if (hidden) View.GONE else View.VISIBLE
            sendButton.setBackgroundResource(R.drawable.btn_santander_red)
            context?.let { sendButton.setTextColor(ContextCompat.getColor(it, R.color.white)) }
            sendButton.gravity = Gravity.CENTER
            mDynamicForm.addView(sendButton)
            setHeight(sendButton, resources.getDimension(R.dimen.material_design_button_height).toInt())
            setTopSpacing(sendButton, topSpacing)
            sendButton.setOnClickListener { validate() }
        }
    }

    private fun maskPhone(phoneField: EditText) {
        phoneField.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                val text = phoneField.text.toString()
                val textLength = phoneField.text?.length

                if (text.endsWith("-") || text.endsWith(" ")) return

                if (textLength == 1) {
                    if (!text.contains("(")) {
                        phoneField.setText(StringBuilder(text).insert(text.length - 1, "(").toString())
                        phoneField.setSelection(phoneField.text.length)
                    }
                } else if (textLength == 4) {
                    if (!text.contains(")")) {
                        phoneField.setText(StringBuilder(text).insert(text.length - 1, ")").toString())
                        phoneField.setSelection(phoneField.text.length)
                    }
                } else if (textLength == 5) {
                    phoneField.setText(StringBuilder(text).insert(text.length - 1, " ").toString())
                    phoneField.setSelection(phoneField.text.length)
                } else if (textLength == 11) {
                    if (!text.contains("-")) {
                        phoneField.setText(StringBuilder(text).insert(text.length - 1, "-").toString())
                        phoneField.setSelection(phoneField.text.length)
                    }
                }

                if (text.length == 15) {
                    val inputManager: InputMethodManager? = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
                    inputManager?.hideSoftInputFromWindow(activity?.currentFocus?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)

                }

            }
        })
    }

    private fun validate() {
        var allValid = true

        var index = 0
        mForm.clear()
        while (index < mContactsTemplate.cells.size) {

            val cell = mContactsTemplate.cells[index]
            val view = mDynamicForm.getChildAt(index)

            ++index
            if (!cell.required || view.visibility != View.VISIBLE) continue
            if (ContactsTemplate.Type.from(cell.type) != ContactsTemplate.Type.Field) continue

            val valid: Boolean = when (ContactsTemplate.TypeField.from(cell.typefield)) {
                ContactsTemplate.TypeField.Text -> validateText(view as EditText)
                ContactsTemplate.TypeField.TelNumber -> validateNumber(view as EditText)
                ContactsTemplate.TypeField.Email -> validateEmail(view as EditText)
            }

            if (valid && cell.id != null) mForm[cell.id!!] = view.text.toString()
            else {
                allValid = false
                break
            }

        }

        if (allValid) EventBus.getDefault().post(SendContactEvent(mForm))
    }

    private fun validateText(field: EditText): Boolean {
        if (field.text.isNotEmpty()) return true
        field.error = getString(R.string.invalid_field)
        field.requestFocus()
        return false
    }

    private fun validateNumber(field: EditText): Boolean {

        val phone = field.text.toString()

        var escapedPhone = when {
            phone.startsWith("+55") -> phone.replaceFirst("+55", "")
            phone.startsWith("55") -> phone.replace("55", "")
            else -> phone
        }
        escapedPhone = escapedPhone.replace("-", "")
        escapedPhone = escapedPhone.replace("(", "")
        escapedPhone = escapedPhone.replace(")", "")
        escapedPhone = escapedPhone.replace(" ", "")

        if (escapedPhone.length != 11) {
            field.error = getString(R.string.invalid_field)
            field.requestFocus()
            return false
        }

        return true

    }

    private fun validateEmail(field: EditText): Boolean {
        val email = field.text.toString()
        if (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()) return true
        field.error = getString(R.string.invalid_field)
        field.requestFocus()
        return false
    }

    private fun searchView(id: Int?): View? {
        if (id == null) return null
        var index = 0
        while (index < mContactsTemplate.cells.size) {
            val cell = mContactsTemplate.cells[index]
            if (cell.id == id) return mDynamicForm.getChildAt(index)
            ++index
        }
        return null
    }

    private fun setTopSpacing(view: View, spacing: Int?) {
        if (spacing == null) return
        val params = view.layoutParams as ViewGroup.MarginLayoutParams
        params.setMargins(0, spacing, 0, 0)
        view.layoutParams = params
    }

    private fun setHeight(view: View, height: Int) {
        val params = view.layoutParams as ViewGroup.MarginLayoutParams
        params.height = height
        view.layoutParams = params
    }

    private fun loadObservers() {
        mViewModel.observe().observe(this, Observer {

            if (it?.hasSucceeded() == true && it.data != null) {
                it.data?.let { mContactsTemplate = it }
                loadValues()
            }

        })
    }

    companion object {
        fun getInstance(): ContactFragment {
            return ContactFragment()
        }
    }

}