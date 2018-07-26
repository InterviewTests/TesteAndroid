package com.rafhack.testeandroid.form.customCells

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import android.widget.ImageButton
import com.rafhack.testeandroid.R
import com.rafhack.testeandroid.data.entities.Cell
import com.rafhack.testeandroid.data.entities.FieldType


class CustomCellType1 : ConstraintLayout {

    var valid: Boolean = true
        private set(value) = updateErrorState(value)
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
                when (fieldType) {
                    FieldType.TEXT -> valid = validateTextField(s?.toString()!!)
                    else -> {
                    }
                }
            }
        })
        imgDelete.setOnClickListener { edtText.setText("") }
    }

    private fun validateTextField(string: String): Boolean {
        return string.isNotEmpty()
    }

    private fun updateCell(cell: Cell?) {
        fieldType = FieldType.from(cell?.typeField!!)
        tilTextInput.hint = cell.message
    }

    private fun updateErrorState(value: Boolean) {
        tilTextInput.error = resources.getString(fieldType.errorMessage)
        tilTextInput.isErrorEnabled = !value
    }
}