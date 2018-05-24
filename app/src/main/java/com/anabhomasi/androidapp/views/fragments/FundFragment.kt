package com.anabhomasi.androidapp.views.fragments

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import com.anabhomasi.androidapp.App

import com.anabhomasi.androidapp.R
import com.anabhomasi.androidapp.R.id.infoRecyclerView
import com.anabhomasi.androidapp.views.adapters.InfoAdapter
import com.anabhomasi.androidapp.views.adapters.MoreInfoAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_TITLE = "title"
private const val ARG_PAGE = "page"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FundFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [FundFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class FundFragment : Fragment() {
    // TODO: Rename and change types of parameters
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_fund, container, false)

        setAllTexts(view)

        when (App.getInstance().funds.screen.risk) {
            1 -> {
                val radio = view.findViewById<RadioButton>(R.id.riskOneView)
                radio.isChecked = true
            }
            2 -> {
                val radio = view.findViewById<RadioButton>(R.id.riskTwoView)
                radio.isChecked = true
            }
            3 -> {
                val radio = view.findViewById<RadioButton>(R.id.riskThreeView)
                radio.isChecked = true
            }
            4 -> {
                val radio = view.findViewById<RadioButton>(R.id.riskFourView)
                radio.isChecked = true
            }
            5 -> {
                val radio = view.findViewById<RadioButton>(R.id.riskFiveView)
                radio.isChecked = true
            }
        }
        configureInvestButton(view)
        setMoreInfoRecyleView(view)
        setInfoRecyleView(view)

        return view
    }

    private fun setMoreInfoRecyleView(view: View?) {
        val recyclerView = view?.findViewById<RecyclerView>(R.id.moreInfoRecyclerView)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = MoreInfoAdapter()
        recyclerView?.setHasFixedSize(true)
    }

    private fun setInfoRecyleView(view: View?) {
        val recyclerView = view?.findViewById<RecyclerView>(R.id.infoRecyclerView)
        recyclerView?.isNestedScrollingEnabled = false
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = InfoAdapter()
        recyclerView?.setHasFixedSize(true)

    }

    private fun setAllTexts(view: View?) {

        with(App.getInstance().funds.screen){
            val titleTxv = view?.findViewById<TextView>(R.id.titleTxv)
            titleTxv?.text = this.title

            val fundNameTxv = view?.findViewById<TextView>(R.id.fundNameTxv)
            fundNameTxv?.text = this.fundName

            val whatIsTxv = view?.findViewById<TextView>(R.id.whatIsTxv)
            whatIsTxv?.text = this.whatIs

            val definitionTxv = view?.findViewById<TextView>(R.id.definitionTxv)
            definitionTxv?.text = this.definition

            val riskTitleTxv = view?.findViewById<TextView>(R.id.riskTitleTxv)
            riskTitleTxv?.text = this.riskTitle

            val infoTitleTxv = view?.findViewById<TextView>(R.id.infoTitleTxv)
            infoTitleTxv?.text = this.infoTitle
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(page: Int) {
        listener?.onFragmentInteraction(page)
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
        // TODO: Update argument type and name
        fun onFragmentInteraction(page: Int)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FundFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(title: String, page: Int) =
                FundFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_TITLE, title)
                        putInt(ARG_PAGE, page)
                    }
                }
    }
    @SuppressLint("ClickableViewAccessibility")
    private fun configureInvestButton(view: View?) {
        val btn = view?.findViewById<Button>(R.id.investButton)

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

                }
            }
            true
        }
    }
}
