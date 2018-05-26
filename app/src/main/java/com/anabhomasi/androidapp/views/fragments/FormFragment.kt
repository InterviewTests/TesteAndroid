package com.anabhomasi.androidapp.views.fragments

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewCompat
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.anabhomasi.androidapp.R
import com.anabhomasi.androidapp.views.adapters.PageAdapter
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.util.Patterns
import android.view.inputmethod.EditorInfo
import kotlinx.android.synthetic.main.fragment_form.*
import java.util.regex.Pattern


private const val ARG_TITLE = "title"
private const val ARG_PAGE = "page"
private const val PHONE_REGEX = "^\\((\\d{2})\\)\\s(\\d{4,5}\\-\\d{4})\$"


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FormFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [FormFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class FormFragment : Fragment() {

    private var title: String? = null
    private var page: Int? = null
    private var shouldContinue = true
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString(ARG_TITLE)
            page = it.getInt(ARG_PAGE)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
// Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_form, container, false)

        configureSendButton(view)
        configureFieldValidation(view, R.id.nameEdTx)
        configureFieldValidation(view, R.id.emailEdTx)
        configureFieldValidation(view, R.id.phoneEdTx)

        return view
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun configureSendButton(view: View?) {
        val btn = view?.findViewById<Button>(R.id.sendButton)

        btn?.setOnTouchListener { button, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    val reducer = AnimatorInflater.loadAnimator(context, R.animator.reduce_size) as AnimatorSet
                    reducer.setTarget(button)
                    val drawable = btn.background as TransitionDrawable

                    reducer.start()
                    drawable.startTransition(300)
                }
                MotionEvent.ACTION_UP -> {
                    val regainer = AnimatorInflater.loadAnimator(context, R.animator.regain_size) as AnimatorSet
                    regainer.setTarget(button)
                    val drawable = btn.background as TransitionDrawable

                    regainer.start()
                    drawable.reverseTransition(300)

                    onSendButtonTouched()
                }
            }
            true
        }
    }

    private fun configureFieldValidation(view: View?, field: Int) {
        val editText = view?.findViewById<TextInputEditText>(field)

        editText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {
                validateEditText(field)
            }
        })
        editText?.setOnEditorActionListener() { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_DONE){
                if (field == R.id.phoneEdTx) {
                    //New max length
                    (v as TextInputEditText).filters = arrayOf(InputFilter.LengthFilter(15))
                    //Format number
                    v.setText(formatPhone(v.text.toString()))
                }

                validateEditText(field)

                true
            } else {
                false
            }
        }
        editText?.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                if (field == R.id.phoneEdTx) {
                    //New max length
                    (v as TextInputEditText).filters = arrayOf(InputFilter.LengthFilter(15))
                    //Format number
                    v.setText(formatPhone(v.text.toString()))
                }

                validateEditText(field)
            } else {

                (v.parent.parent as TextInputLayout).error = null
                if (field == R.id.phoneEdTx){
                    (v as TextInputEditText).filters = arrayOf(InputFilter.LengthFilter(11))
                }
            }
        }
    }

    private fun formatPhone (wholePhone: String):String{
        val cleanPhone = wholePhone
                .replace("(", "")
                .replace(")", "")
                .replace(" ", "")
                .replace("-", "")

        if (cleanPhone.length >= 10){
            val ddd = cleanPhone.substring(0..1)
            val phoneLeft = cleanPhone.substring(2..cleanPhone.length-5)
            val phoneRight = cleanPhone.substring(cleanPhone.length-4 until cleanPhone.length)
            return "($ddd) $phoneLeft-$phoneRight"
        }

        return wholePhone
    }


    private fun validateEditText(field: Int) {
        val editTx = view?.findViewById<TextInputEditText>(field)
        val txLayout = editTx?.parent?.parent as TextInputLayout

        val emailValidation =
                if (field == R.id.emailEdTx)
                    Patterns.EMAIL_ADDRESS.matcher(editTx.text).matches()
                else
                    true

        val phoneValidation =
                if (field == R.id.phoneEdTx)
                    Pattern.compile(PHONE_REGEX).matcher(editTx.text).matches()
                else
                    true

        if (editTx.text.isNotBlank() && emailValidation && phoneValidation) {
            txLayout.error = null
            val colorStateList = ColorStateList.valueOf(ContextCompat.getColor(context!!, R.color.validEditText))
            ViewCompat.setBackgroundTintList(editTx, colorStateList)
        } else {
            shouldContinue = false
            val hint = txLayout.hint
            txLayout.error = "$hint Inválido"
        }
    }

    private fun validateAllFields() {
        shouldContinue = true

        validateEditText(R.id.phoneEdTx)
        validateEditText(R.id.emailEdTx)
        validateEditText(R.id.nameEdTx)
    }


    private fun onSendButtonTouched() {
        validateAllFields()

        if (shouldContinue) {
            clearAllFields()
            listener?.onFragmentInteraction(PageAdapter.SUCCESS_FORM_PAGE)
        }
    }

    private fun clearAllFields() {

        checkBox.isChecked = false

        emailEdTx.text.clear()
        emailTxInLayout.error = null

        phoneEdTx.text.clear()
        phoneTxInLayout.error = null

        nameEdTx.text.clear()
        nameEdTx.requestFocus()
        nameTxInLayout.error = null

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
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(page: Int)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param title Parameter 1.
         * @param page Parameter 2.
         * @return A new instance of fragment FormFragment.
         */
        @JvmStatic
        fun newInstance(title: String, page: Int) =
                FormFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_TITLE, title)
                        putInt(ARG_PAGE, page)
                    }
                }
    }
}