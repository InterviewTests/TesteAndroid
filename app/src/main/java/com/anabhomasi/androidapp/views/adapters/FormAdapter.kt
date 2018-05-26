package com.anabhomasi.androidapp.views.adapters

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.annotation.SuppressLint
import android.graphics.drawable.TransitionDrawable
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.InputFilter
import android.text.InputType
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.CheckBox
import com.anabhomasi.androidapp.App
import com.anabhomasi.androidapp.R
import com.anabhomasi.androidapp.data.models.Form
import com.anabhomasi.androidapp.views.fragments.DynamicFormFragment
import kotlinx.android.synthetic.main.layout_dynamic_form_row.view.*

class FormAdapter(val fragment: DynamicFormFragment): RecyclerView.Adapter<FormViewHolder>() {

    private lateinit var mParentView : View
    private var topSpacingTotal = 0.0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FormViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        mParentView = parent

        val rowView = when (viewType) {
            Form.TYPE_FIELD -> layoutInflater.inflate(R.layout.layout_dynamic_form_field, parent, false)
            Form.TYPE_IMAGE -> layoutInflater.inflate(R.layout.layout_dynamic_form_image, parent, false)
            Form.TYPE_CHECKBOX -> layoutInflater.inflate(R.layout.layout_dynamic_form_checkbox, parent, false)
            Form.TYPE_SEND -> layoutInflater.inflate(R.layout.layout_dynamic_form_button, parent, false)
            Form.TYPE_TEXT -> layoutInflater.inflate(R.layout.layout_dynamic_form_text, parent, false)
            else -> layoutInflater.inflate(R.layout.layout_dynamic_form_text, parent, false)
        }

        return FormViewHolder(rowView)
}

    override fun getItemCount(): Int {
        return App.getInstance().form.cells.size
    }

    override fun getItemViewType(position: Int): Int {
        return App.getInstance().form.cells[position].type
    }

    @SuppressLint("SetTextI18n", "ClickableViewAccessibility")
    override fun onBindViewHolder(holder: FormViewHolder, position: Int) {
        holder.view.apply {
            with (App.getInstance().form.cells[position]){
                when (getItemViewType(position)){
                    Form.TYPE_FIELD -> {
                        dynamicTextInputLayout.apply {
                            tag = App.getInstance().form.cells[position].id
                            visibility = if (hidden) View.GONE else View.VISIBLE
                            hint = message
                            editText?.inputType = when (Form.TypeField.from(typefield?.toUpperCase())?.code) {
                                        Form.TypeField.TEXT.code -> InputType.TYPE_CLASS_TEXT
                                        Form.TypeField.EMAIL.code -> InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                                        Form.TypeField.TELNUMBER.code -> {
                                            editText?.filters = arrayOf(InputFilter.LengthFilter(11))
                                            InputType.TYPE_CLASS_PHONE
                                        }
                                        else -> InputType.TYPE_CLASS_TEXT
                            }

                            (this.editText as? TextInputEditText)?.configureFieldValidation()

                            this.setTopSpacing(topSpacing)

                            if (show != null) {
                                this.setShowClickListener(show)
                            }
                        }
                    }
                    Form.TYPE_TEXT -> {
                        dynamicTextView.apply {
                            tag = App.getInstance().form.cells[position].id
                            visibility = if (hidden) View.GONE else View.VISIBLE
                            text = message

                            this.setTopSpacing(topSpacing)

                            if (show != null) {
                                this.setShowClickListener(show)
                            }
                        }
                    }
                    Form.TYPE_IMAGE -> {
                        dynamicImageView.apply {
                            tag = App.getInstance().form.cells[position].id
                            visibility = if (hidden) View.GONE else View.VISIBLE

                            this.setTopSpacing(topSpacing)

                            if (show != null) {
                                this.setShowClickListener(show)
                            }
                        }
                    }
                    Form.TYPE_CHECKBOX -> {
                        dynamicCheckBox.apply {
                            tag = App.getInstance().form.cells[position].id
                            visibility = if (hidden) View.GONE else View.VISIBLE
                            text = message

                            this.setTopSpacing(topSpacing)

                            if (show != null) {
                                (this as CheckBox).setShowClickListener(show)
                            }
                        }
                    }
                    Form.TYPE_SEND -> {
                        dynamicButton.apply {
                            tag = App.getInstance().form.cells[position].id
                            visibility = if (hidden) View.GONE else View.VISIBLE
                            text = message

                            this.setTopSpacing(topSpacing)

                            this.setOnTouchListener { button, event ->
                                when (event.action) {
                                    MotionEvent.ACTION_DOWN -> {
                                        val reducer = AnimatorInflater.loadAnimator(context, R.animator.reduce_size) as AnimatorSet
                                        reducer.setTarget(button)
                                        val drawable = this.background as TransitionDrawable

                                        reducer.start()
                                        drawable.startTransition(300)
                                    }
                                    MotionEvent.ACTION_UP -> {
                                        val regainer = AnimatorInflater.loadAnimator(context, R.animator.regain_size) as AnimatorSet
                                        regainer.setTarget(button)
                                        val drawable = this.background as TransitionDrawable

                                        regainer.start()
                                        drawable.reverseTransition(300)

                                        onSendButtonTouched()
                                    }
                                }
                                true
                            }
                        }
                    }
                    else -> { }
                }
            }
        }
    }

    private fun onSendButtonTouched() {
        fragment.onButtonPressed(PageAdapter.SUCCESS_FORM_PAGE)
    }

    private fun TextInputEditText.configureFieldValidation() {
        val textInputEditText = this

        this.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {
                fragment.validateField(textInputEditText, s)
            }
        })
        this.setOnEditorActionListener { v, actionId, _ ->
            if(actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT){

                if (textInputEditText.inputType == InputType.TYPE_CLASS_PHONE) {
                    //New max length
                    (v as TextInputEditText).filters = arrayOf(InputFilter.LengthFilter(15))
                    //Format number
                    v.setText(v.text.toString().maskPhone())
                }

                fragment.validateField(textInputEditText)

                true
            } else {
                false
            }
        }
        this.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                if (textInputEditText.inputType == InputType.TYPE_CLASS_PHONE) {
                    //New max length
                    (v as TextInputEditText).filters = arrayOf(InputFilter.LengthFilter(15))
                    //Format number
                    v.setText(v.text.toString().maskPhone())
                }

                fragment.validateField(textInputEditText)
            } else {
                (v.parent.parent as TextInputLayout).error = null
            }
        }
    }

    private fun String.maskPhone ():String{
        val cleanPhone = this.unmaskPhone()

        return if (cleanPhone.length >= 10){
            val ddd = cleanPhone.substring(0..1)
            val phoneLeft = cleanPhone.substring(2..cleanPhone.length-5)
            val phoneRight = cleanPhone.substring(cleanPhone.length-4 until cleanPhone.length)
            "($ddd) $phoneLeft-$phoneRight"
        }
        else this
    }
    private fun String.unmaskPhone():String{
        return this
                .replace("(", "")
                .replace(")", "")
                .replace(" ", "")
                .replace("-", "")
    }

    private fun View.setShowClickListener(show: Int){
        val viewToBeShown = mParentView.findViewWithTag<View>(show)

        if (this is CheckBox) this.setOnCheckedChangeListener { _, isChecked ->
            viewToBeShown?.visibility =
                        if (isChecked) View.VISIBLE
                        else View.GONE
        } else {
            this.setOnClickListener {
                viewToBeShown?.visibility =
                        if (viewToBeShown.visibility == View.VISIBLE) View.GONE
                        else View.VISIBLE

            }
        }

        viewToBeShown?.setTopSpacing(App.getInstance().form.cells[show].topSpacing)
    }

    private fun View.setTopSpacing(topSpacing: Double){
        //if (this.visibility == View.VISIBLE){
            topSpacingTotal += topSpacing
            val marginLayoutParams = this.layoutParams as ViewGroup.MarginLayoutParams
            marginLayoutParams.setMargins(33, topSpacing.toInt(), 33, 0)
            this.layoutParams = marginLayoutParams
        //} else {
        //   this.layoutParams = RecyclerView.LayoutParams(0, 0)
        //}

    }
}

class FormViewHolder(val view: View): RecyclerView.ViewHolder(view)