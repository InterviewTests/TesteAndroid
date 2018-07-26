package com.rafhack.testeandroid.form.customCells

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import com.rafhack.testeandroid.R
import com.rafhack.testeandroid.data.entities.Cell

class CustomCellType2 : ConstraintLayout {

    var cell: Cell? = null
        set(value) = updateCell(value)

    private lateinit var tvwText: TextView

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        setupView()
    }

    private fun setupView() {
        View.inflate(context, R.layout.dynamic_form_cell_type_2, this)
        tvwText = findViewById(R.id.dynamic_form_cell_type_2_tvw_text)
    }

    private fun updateCell(value: Cell?) {
        tvwText.text = value?.message
    }

}