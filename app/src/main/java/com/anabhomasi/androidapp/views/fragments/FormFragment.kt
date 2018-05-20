package com.anabhomasi.androidapp.views.fragments

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.anabhomasi.androidapp.R
import com.anabhomasi.androidapp.views.adapters.PageAdapter


private const val ARG_TITLE = "title"
private const val ARG_PAGE = "page"

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
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString(ARG_TITLE)
            page = it.getInt(ARG_PAGE)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
// Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_form, container, false)

        val btn = view.findViewById<Button>(R.id.sendButton)

        btn.setOnTouchListener { button, event ->
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

        return view
    }

    private fun onSendButtonTouched() {
        //TODO validate form

        listener?.onFragmentInteraction(PageAdapter.SUCCESS_FORM_PAGE)
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