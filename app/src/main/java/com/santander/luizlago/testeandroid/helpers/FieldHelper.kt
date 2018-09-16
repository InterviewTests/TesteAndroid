package com.santander.luizlago.testeandroid.helpers

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat
import android.support.v4.view.ViewCompat
import android.support.v4.widget.CompoundButtonCompat
import android.text.InputType
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import com.santander.luizlago.testeandroid.R
import com.santander.luizlago.testeandroid.api.models.Cell
import com.santander.luizlago.testeandroid.enums.TypeField
import com.santander.luizlago.testeandroid.enums.TypeFieldValueOfOrNull

class FieldHelper {

    companion object {
        fun configure(view: View, cell: Cell) {
            setTopSpacing(view, cell.topSpacing.toInt())
            setVisibility(view, cell.hidden)
            when(view) {
                is CheckBox -> configureCheckBox(view, cell)
                is Button -> configureButton(view, cell)
                is EditText -> configureEditText(view, cell)
                is TextView -> configureTextView(view, cell)
            }
        }

        fun setTopSpacing(view: View, space: Int) {
            val marginLayoutParams = ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            marginLayoutParams.topMargin = convertDpToPx(view.context, space)
            marginLayoutParams.marginStart = convertDpToPx(view.context, 24)
            marginLayoutParams.marginEnd = convertDpToPx(view.context, 24)
            view.layoutParams = marginLayoutParams
        }

        fun setVisibility(view: View, isHidden: Boolean) {
            view.visibility = if (isHidden) View.GONE else View.VISIBLE
        }

        fun convertDpToPx(context: Context, dp: Int): Int {
            val displayMetrics = context.resources.displayMetrics
            return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
        }

        fun configureTextView(view: View, cell: Cell) {
            val textView = view as TextView
            textView.text = cell.message
            textView.textSize = 15.0f
            val typeface = ResourcesCompat.getFont(view.context, R.font.din_pro_medium)
            view.typeface = typeface
        }

        fun configureEditText(view: View, cell: Cell) {
            val editTextView = view as EditText
            editTextView.hint = cell.message
            val typeface = ResourcesCompat.getFont(view.context, R.font.din_pro_light)
            view.typeface = typeface
            ViewCompat.setBackgroundTintList(view, ColorStateList.valueOf(Color.GRAY))
            setEditTextInputType(editTextView, cell.typefield!!)
        }

        fun setEditTextInputType(editTextView: EditText, typeField: String) {
            val enumType = TypeFieldValueOfOrNull(typeField)
            when(enumType) {
                TypeField.TEXT -> editTextView.inputType = InputType.TYPE_CLASS_TEXT
                TypeField.EMAIL -> editTextView.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                TypeField.TELNUMBER -> configureEditTextFieldForPhoneNumber(editTextView)
                else -> return
            }
        }

        fun configureEditTextFieldForPhoneNumber(editTextView: EditText) {
            editTextView.inputType = InputType.TYPE_CLASS_NUMBER
            MaskHelper().addWatcher(editTextView)
        }

        fun configureCheckBox(view: View, cell: Cell) {
            val checkBox = view as CheckBox
            checkBox.text = cell.message
            val typeface = ResourcesCompat.getFont(view.context, R.font.din_pro_light)
            view.typeface = typeface
            view.setLinkTextColor(ContextCompat.getColor(view.context, R.color.colorPrimary))
            val states = arrayOf(intArrayOf(-android.R.attr.state_enabled), // disabled
                    intArrayOf(-android.R.attr.state_checked), // unchecked
                    intArrayOf()  // default
            )
            val colors = intArrayOf(-0x1, -0x15fbfc, -0x15fbfc)
            CompoundButtonCompat.setButtonTintList(checkBox, ColorStateList(states, colors))
        }

        fun configureButton(view: View, cell: Cell) {
            val button = view as Button
            button.text = cell.message
            button.setTextColor(ContextCompat.getColor(view.context, android.R.color.white))
            button.setAllCaps(false)
            button.background = ContextCompat.getDrawable(view.context, R.drawable.background_rounded_red_button_selector)
            val typeface = ResourcesCompat.getFont(view.context, R.font.din_pro_medium)
            view.typeface = typeface
        }

        fun findFieldInContainer(viewGroup: ViewGroup, cellId: Int): View? {
            val childCount = viewGroup.childCount
            for (idx in 0..childCount) {
                val view = viewGroup.getChildAt(idx)
                val tag = view.tag
                if (tag != null) {
                    if (tag is Cell) {
                        if (tag.id == cellId) {
                            return view
                        }
                    }
                }
            }
            return null
        }

        fun verifyRequiredFields(viewGroup: ViewGroup) : Boolean {
            val childCount = viewGroup.childCount
            for (idx in 0..childCount) {
                val view = viewGroup.getChildAt(idx)
                if (view != null) {
                    val tag = view.tag
                    if (tag != null) {
                        if (tag is Cell) {
                            if (view is EditText) {
                                if (validateEditTextField(view, tag))
                                    return true
                            }
                        }
                    }
                }
            }
            return false
        }

        fun validateEditTextField(view: View, cell: Cell): Boolean {
            var error = false
            val value = (view as EditText).text.toString()
            val enumType = TypeFieldValueOfOrNull(cell.typefield!!)

            if (view.visibility == View.VISIBLE) {
                error =
                        when(enumType) {
                            TypeField.EMAIL -> !ValidationHelper.isValidEmail(value)
                            TypeField.TELNUMBER -> ValidationHelper.isValidPhoneNumber(value)
                            TypeField.TEXT -> ValidationHelper.isValidText(value)
                            else -> false
                        }

                if (error) {
                    ViewCompat.setBackgroundTintList(view, ColorStateList.valueOf(Color.RED))
                } else {
                    ViewCompat.setBackgroundTintList(view, ColorStateList.valueOf(Color.GREEN))
                }
            }

            return error
        }

        fun getValuesFromFields(viewGroup: ViewGroup): Map<Int, String> {
            val values = HashMap<Int, String>()

            val childCount = viewGroup.childCount
            for (idx in 0..childCount) {
                val view = viewGroup.getChildAt(idx)
                if (view != null) {
                    val tag = view.tag
                    if (tag != null) {
                        if (tag is Cell) {
                            if (view is EditText) {
                                values.put(tag.id, view.text.toString())
                            }
                        }
                    }
                }
            }

            return values
        }

        fun removeAllFields(viewGroup: ViewGroup) {
            val childCount = viewGroup.childCount
//            for (idx in 1..childCount) {
//                val view = viewGroup.getChildAt(idx)
                viewGroup.removeViewsInLayout(1, childCount-1)
//            }
//            viewGroup.invalidate()
        }

    }

}

