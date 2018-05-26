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
import com.anabhomasi.androidapp.views.adapters.InfoAdapter
import com.anabhomasi.androidapp.views.adapters.MoreInfoAdapter

class FundFragment : Fragment() {
    private var listener: OnFragmentInteractionListener? = null

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

        fun onFragmentInteraction(page: Int)
    }

    companion object {
        @JvmStatic
        fun newInstance() = FundFragment()
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
