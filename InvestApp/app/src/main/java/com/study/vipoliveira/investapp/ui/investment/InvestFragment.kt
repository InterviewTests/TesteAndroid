package com.study.vipoliveira.investapp.ui.investment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.study.vipoliveira.investapp.R
import dagger.android.support.AndroidSupportInjection

import javax.inject.Inject

class InvestFragment: Fragment(), InvestmentContract.View{

    @Inject
    lateinit var presenter: InvestmentContract.Presenter

    private var root: View? = null

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_invest, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        presenter.getInvestments()
    }
    override fun displayLoadingUI() {
    }

    override fun hideLoadingUI() {
    }

}