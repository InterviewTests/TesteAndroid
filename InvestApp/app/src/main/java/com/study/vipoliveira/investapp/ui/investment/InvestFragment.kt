package com.study.vipoliveira.investapp.ui.investment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.study.vipoliveira.investapp.R
import com.study.vipoliveira.investapp.data.network.investment.entities.Info
import com.study.vipoliveira.investapp.data.network.investment.entities.MoreInfo
import com.study.vipoliveira.investapp.data.network.investment.entities.Screen
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_invest.*
import kotlinx.android.synthetic.main.layout_network_state.*
import kotlinx.android.synthetic.main.layout_risk_graph.*


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
        loading_progress_bar.visibility = View.VISIBLE
        retryButtonSetUp()
    }

    private fun retryButtonSetUp() {
        retry_button.visibility = View.GONE
        retry_button.setOnClickListener{presenter.getInvestments()}
    }

    private fun displayRetryButton(){
        retry_button.visibility = View.VISIBLE
    }

    override fun hideLoadingUI() {
        loading_progress_bar.visibility = View.GONE
    }

    override fun displayError(error: String) {
        error_msg_txt.visibility = View.VISIBLE
        error_msg_txt.text = error
        hideLoadingUI()
        displayRetryButton()
    }

    private fun hideError(){
        error_msg_txt.visibility = View.GONE
    }
    override fun updateInvestScreen(screen: Screen) {
        hideError()
        constraintInvest.visibility = View.VISIBLE
        titleTxt.text = screen.title
        fundNameTxt.text = screen.fundName
        whatIsTxt.text = screen.whatIs
        definitionTxt.text = screen.definition
        riskTitleTxt.text = screen.riskTitle
        infoTitleTxt.text = screen.infoTitle
        setRisk(screen.risk)
        setTaxes(screen.moreInfo)
        updateInvestList(screen)
    }

    private fun setRisk(risk: Int) {
        when (risk){
            1 -> arrowOne.visibility = View.VISIBLE
            2 -> arrowTwo.visibility = View.VISIBLE
            3 -> arrowThree.visibility = View.VISIBLE
            4 -> arrowFour.visibility = View.VISIBLE
            5 -> arrowFive.visibility = View.VISIBLE
        }
    }

    private fun setTaxes(taxInfo: MoreInfo) {
        monthCdiTxt.text = getString(R.string.percent, taxInfo.month.cdi.toString())
        monthFundTxt.text = getString(R.string.percent, taxInfo.month.fund.toString())

        yearCdiTxt.text = getString(R.string.percent, taxInfo.year.cdi.toString())
        yearFundTxt.text = getString(R.string.percent, taxInfo.year.fund.toString())

        monthsCdiTxt.text = getString(R.string.percent, taxInfo.twelveMonths.cdi.toString())
        monthsFundTxt.text = getString(R.string.percent, taxInfo.twelveMonths.fund.toString())
    }

    private fun updateInvestList(screen: Screen) {
        val concatList: ArrayList<Info> = ArrayList()
        concatList.addAll(screen.info)
        concatList.addAll(screen.downInfo)

        val investAdapter = InvestListAdapter(concatList)
        val linearLayoutManager = LinearLayoutManager(context)
        recycleViewInfo.layoutManager = linearLayoutManager
        recycleViewInfo.adapter = investAdapter
        investAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        presenter.clearDiposable()
        super.onDestroy()
    }
}