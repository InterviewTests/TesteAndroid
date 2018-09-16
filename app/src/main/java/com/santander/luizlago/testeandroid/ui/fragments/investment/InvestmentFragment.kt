package com.santander.luizlago.testeandroid.ui.fragments.investment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.santander.luizlago.testeandroid.R
import com.santander.luizlago.testeandroid.api.models.Fund
import com.santander.luizlago.testeandroid.commons.BaseFragment
import com.santander.luizlago.testeandroid.ui.fragments.investment.components.InvestmentHeaderView
import kotlinx.android.synthetic.main.fragment_investment.*

class InvestmentFragment : BaseFragment<InvestmentContract.Presenter>(), InvestmentContract.View{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_investment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun createPresente(): InvestmentContract.Presenter = InvestmentPresenter(this)

    override fun showLoadingIndication(isShow: Boolean) {
    }

    override fun addHeaderView(fund: Fund) {
        val headerView = InvestmentHeaderView(this.context!!)
        headerView.setFund(fund)
        this.investmentContainer.addView(headerView)
    }

    companion object {
        @JvmStatic
        fun newInstance() = InvestmentFragment()
    }

}
