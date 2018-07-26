package com.rafhack.testeandroid.form.customCells

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.text.Editable
import android.text.InputFilter
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Patterns.EMAIL_ADDRESS
import android.view.View
import android.view.View.OnFocusChangeListener
import android.widget.EditText
import android.widget.ImageButton
import com.rafhack.testeandroid.R
import com.rafhack.testeandroid.data.entities.Cell
import com.rafhack.testeandroid.data.entities.FieldType
import java.util.regex.Pattern


class CustomCellType1 : ConstraintLayout {

    private var valid: Boolean = true
        set(value) = updateErrorState(value)
    var cell: Cell? = null
        set(value) = updateCell(value)
    private var fieldType: FieldType = FieldType.TEXT

    private lateinit var edtText: TextInputEditText
    private lateinit var tilTextInput: TextInputLayout

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        setupView()
    }

    private fun setupView() {
        View.inflate(context, R.layout.dynamic_form_cell_type_1, this)
        val imgDelete = findViewById<ImageButton>(R.id.dynamic_form_cell_type_1_imb_clear)
        tilTextInput = findViewById(R.id.dynamic_form_cell_type_1_til_text_input)
        edtText = findViewById(R.id.dynamic_form_cell_type_1_edit_text)
        edtText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                performValidation(s?.toString()!!)
            }
        })

        edtText.onFocusChangeListener = OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) performValidation((v as EditText).text.toString())
        }

        imgDelete.setOnClickListener { edtText.setText("") }
    }

    private fun performValidation(s: String) {
        when (fieldType) {
            FieldType.TEXT -> valid = validateTextField(s)
            FieldType.EMAIL_ADDRESS -> valid = validateEmailField(s)
            FieldType.PHONE_NUMBER -> valid = validatePhoneField(s)
            else -> {
            }
        }
    }

    private fun validatePhoneField(string: String): Boolean {
        return string.isNotEmpty() && Pattern.compile(
                "[(]\\d{2}[)]\\s\\d{4,5}-\\d{4}").matcher(string).matches()
    }

    private fun validateTextField(string: String): Boolean {
        return string.isNotEmpty()
    }

    private fun validateEmailField(string: String): Boolean {
        return string.isNotEmpty() && EMAIL_ADDRESS.matcher(string).matches()
    }

    private fun updateCell(cell: Cell?) {
        fieldType = FieldType.from(cell?.typefield!!)
        if (fieldType == FieldType.PHONE_NUMBER) {
            edtText.inputType = InputType.TYPE_CLASS_PHONE
            edtText.addTextChangedListener(PhoneWatcher())
            edtText.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(15))
        }
        tilTextInput.hint = cell.message
    }

    private fun updateErrorState(value: Boolean) {
        tilTextInput.error = resources.getString(fieldType.errorMessage)
        tilTextInput.isErrorEnabled = !value
    }

    inner class PhoneWatcher : TextWatcher {
        private var isRunning = false
        private var isDeleting = false
        private var autoAddind = false
        private val mask1 = "(##) ####-####"
        private val mask2 = "(##) #####-####"

        override fun afterTextChanged(s: Editable?) {
            if (isRunning || isDeleting) return
            isRunning = true
            val length = s?.length as Int
            val maskToCompare = if (autoAddind) mask2 else mask1
            when {
                length < maskToCompare.length -> processText(s, maskToCompare)
                length <= mask2.length && length > mask1.length -> if (!autoAddind) {
                    autoAddind = true
                    val phone = s.toString()
                            .replace("(", "")
                            .replace(")", "")
                            .replace(" ", "")
                            .replace("-", "")
                    edtText.setText("")
                    isRunning = false
                    phone.forEach {
                        edtText.setText(edtText.text.toString().plus(it))
                    }
                    edtText.setSelection(edtText.text.length)
                    autoAddind = false
                }
            }
            isRunning = false
        }

        private fun processText(s: Editable, mask: String) {
            val length = s.length
            if (mask[length] != '#')
                s.append(mask[length])
            else if (mask[length - 1] != '#')
                s.insert(length - 1, mask, length - 1, length)
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            isDeleting = count > after
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

    }
}