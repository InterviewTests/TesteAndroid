package com.rafhack.testeandroid.investment

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.rafhack.testeandroid.R
import com.rafhack.testeandroid.base.BaseProgressFragment
import com.rafhack.testeandroid.data.entities.investment.Investment
import com.rafhack.testeandroid.di.component.DaggerFragmentComponent
import com.rafhack.testeandroid.di.module.FragmentModule
import com.rafhack.testeandroid.investment.riskView.RiskView
import javax.inject.Inject

class InvestmentFragment : BaseProgressFragment(), InvestmentContract.View {

    @Inject
    lateinit var presenter: InvestmentPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerFragmentComponent.builder()
                .fragmentModule(FragmentModule())
                .build().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        presenter.loadInvestments()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?): View {
        return inflater.inflate(R.layout.fragment_investment, container, true)
    }

    override fun setProgress(active: Boolean) {
        if (active) showProgress() else hideProgress()
    }

    override fun showErrorMessage(message: String) {
        Snackbar.make(view?.findViewById(R.id.fragment_base_progress_ctl_root)!!,
                message, Snackbar.LENGTH_LONG).show()
    }

    @SuppressLint("SetTextI18n")
    override fun showInvestmentInfo(investment: Investment) {
        with(view as View) {
            val tvwTitle = findViewById<TextView>(R.id.fragment_investment_tvw_title)
            val tvwFundName = findViewById<TextView>(R.id.fragment_investment_tvw_fund_name)
            val tvwWhatIs = findViewById<TextView>(R.id.fragment_investment_tvw_what_is)
            val tvwDefinition = findViewById<TextView>(R.id.fragment_investment_tvw_definition)
            val tvwRiskTitle = findViewById<TextView>(R.id.fragment_investment_tvw_risk_title)
            val rkvRisk = findViewById<RiskView>(R.id.fragment_investment_rkv_risk)
            val tvwInfoTitle = findViewById<TextView>(R.id.fragment_investment_tvw_info_title)
            val tvwMonthFund = findViewById<TextView>(R.id.investment_more_info_tvw_month_fund)
            val tvwYearFund = findViewById<TextView>(R.id.investment_more_info_tvw_year_fund)
            val tvw12MonthFund = findViewById<TextView>(R.id.investment_more_info_tvw_12_month_fund)
            val tvwMonthDI = findViewById<TextView>(R.id.investment_more_info_tvw_month_di)
            val tvwYearDI = findViewById<TextView>(R.id.investment_more_info_tvw_year_di)
            val tvw12MonthDI = findViewById<TextView>(R.id.investment_more_info_tvw_12_month_di)
            val rcvInfo = findViewById<RecyclerView>(R.id.fragment_investment_rcv_info)
            val rcvDownInfo = findViewById<RecyclerView>(R.id.fragment_investment_rcv_down_info)
            val infoLayoutManager = object : LinearLayoutManager(context) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }
            val downInfoLayoutManager = object : LinearLayoutManager(context) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }

            tvwTitle.text = investment.title
            tvwFundName.text = investment.fundName
            tvwWhatIs.text = investment.whatIs
            tvwDefinition.text = investment.definition
            tvwRiskTitle.text = investment.riskTitle
            rkvRisk.risk = investment.risk
            tvwInfoTitle.text = investment.infoTitle
            tvwMonthFund.text = "%.2f".format(investment.moreInfo.month.fund).plus("%")
            tvwYearFund.text = "%.2f".format(investment.moreInfo.year.fund).plus("%")
            tvw12MonthFund.text = "%.2f".format(investment.moreInfo.twelveMonths.fund).plus("%")
            tvwMonthDI.text = "%.2f".format(investment.moreInfo.month.cdi).plus("%")
            tvwYearDI.text = "%.2f".format(investment.moreInfo.year.cdi).plus("%")
            tvw12MonthDI.text = "%.2f".format(investment.moreInfo.twelveMonths.cdi).plus("%")
            rcvInfo.layoutManager = infoLayoutManager
            rcvInfo.adapter = InvestmentInfoAdapter(investment.info)
            rcvDownInfo.layoutManager = downInfoLayoutManager
            rcvDownInfo.adapter = InvestmentInfoAdapter(investment.downInfo)
        }
    }

}