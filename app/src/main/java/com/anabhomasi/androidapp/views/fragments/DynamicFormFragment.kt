package com.anabhomasi.androidapp.views.fragments

import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.InputType
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import com.anabhomasi.androidapp.App
import com.anabhomasi.androidapp.R
import com.anabhomasi.androidapp.data.models.Form
import com.anabhomasi.androidapp.views.adapters.FormAdapter
import kotlinx.android.synthetic.main.fragment_dynamic_form.*
import java.util.regex.Pattern

private const val PHONE_REGEX = "^\\((\\d{2})\\)\\s(\\d{4,5}\\-\\d{4})\$"

class DynamicFormFragment : Fragment() {

    var listener: OnFragmentInteractionListener? = null
    private var shouldContinue = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dynamic_form, container, false)

        setFormRecyclerView(view)

        return view
    }

    private fun setFormRecyclerView(view: View?) {
        val recyclerView = view?.findViewById<RecyclerView>(R.id.formRecyclerView)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = FormAdapter(this)
    }

    fun onButtonPressed(page: Int) {
        validateAllFields()

        if (shouldContinue) {
            clearAllFields()
            listener?.onFragmentInteraction(page)
        }
    }

    fun validateField(editText: TextInputEditText, s: Editable? = null){
        val txLayout = editText.parent.parent as TextInputLayout
        val content = s ?: editText.text

        val emailValidation =
                if (editText.inputType == InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS)
                    Patterns.EMAIL_ADDRESS.matcher(content).matches()
                else
                    true

        val phoneValidation =
                if (editText.inputType == InputType.TYPE_CLASS_PHONE)
                    Pattern.compile(PHONE_REGEX).matcher(content).matches()
                else
                    true

        if (content.isNotBlank() && emailValidation && phoneValidation){
            txLayout.error = null
            val colorStateList = ColorStateList.valueOf(ContextCompat.getColor(editText.context, R.color.validEditText))
            ViewCompat.setBackgroundTintList(editText, colorStateList)
        } else {
            shouldContinue = false
            val hint = txLayout.hint
            txLayout.error = "$hint Inválido"
        }
    }


    private fun validateAllFields() {
        shouldContinue = true

        App.getInstance().form.cells.forEach {cell ->
            if (cell.required){
                val itemView = when (cell.type){
                    Form.TYPE_FIELD -> formRecyclerView.findViewWithTag<TextInputLayout>(cell.id)
                    //Form.TYPE_IMAGE -> formRecyclerView.findViewWithTag<ImageView>(cell.id)
                    //Form.TYPE_CHECKBOX -> formRecyclerView.findViewWithTag<CheckBox>(cell.id)
                    //Form.TYPE_SEND -> formRecyclerView.findViewWithTag<Button>(cell.id)
                    //Form.TYPE_TEXT -> formRecyclerView.findViewWithTag<TextView>(cell.id)
                    else -> null

                }

                if (itemView?.visibility == View.VISIBLE /*&& itemView is TextInputLayout*/){
                    validateField(itemView.editText as TextInputEditText)
                }
            }
        }
    }

    private fun clearAllFields() {

        for (i in 0 until formRecyclerView.childCount){
            val itemView = formRecyclerView.getChildAt(i)
            if (itemView is TextInputLayout){
                itemView.editText?.text?.clear()
                itemView.error = null
            }
            else if (itemView is CheckBox){
                itemView.isChecked = false
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(page: Int)
    }

    companion object {
        @JvmStatic
        fun newInstance() = DynamicFormFragment()
    }
}
