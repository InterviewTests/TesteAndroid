package com.santander.android.ui

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.santander.android.R
import com.santander.android.model.template.FundTemplate
import com.santander.android.viewmodel.FundViewModel

class FundFragment : Fragment() {

    private lateinit var mViewModel: FundViewModel
    private var mFundTemplate = FundTemplate()

    // Views
    private lateinit var mBodyContainer: View
    private lateinit var mProgressContainer: View
    private lateinit var mEmptyMessageContainer: View
    private lateinit var mTitle: TextView
    private lateinit var mFundName: TextView
    private lateinit var mWhatIs: TextView
    private lateinit var mDefinition: TextView
    private lateinit var mRiskTitle: TextView
    private lateinit var mRiskBar: LinearLayout
    private lateinit var mInfoTitle: TextView
    private lateinit var mMonthFund: TextView
    private lateinit var mMonthCDI: TextView
    private lateinit var mYearFund: TextView
    private lateinit var mYearCDI: TextView
    private lateinit var m12MonthFund: TextView
    private lateinit var m12MonthCDI: TextView
    private lateinit var mInfoContainer: LinearLayout
    private lateinit var mInvestButton: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let { mViewModel = FundViewModel.create(it) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_fund, container, false)
        loadViews(rootView)
        loadObservers()
        return rootView
    }

    private fun loadViews(rootView: View) {
        mBodyContainer = rootView.findViewById(R.id.fragment_fund_body_container)
        mProgressContainer = rootView.findViewById(R.id.fragment_fund_progress_container)
        mEmptyMessageContainer = rootView.findViewById(R.id.fragment_fund_empty_message_container)
        mTitle = rootView.findViewById(R.id.fragment_fund_title)
        mFundName = rootView.findViewById(R.id.fragment_fund_fund_name)
        mWhatIs = rootView.findViewById(R.id.fragment_fund_what_is)
        mDefinition = rootView.findViewById(R.id.fragment_fund_definition)
        mRiskTitle = rootView.findViewById(R.id.fragment_fund_risk_title)
        mRiskBar = rootView.findViewById(R.id.fragment_fund_risk_bar)
        mInfoTitle = rootView.findViewById(R.id.fragment_fund_info_title)
        mMonthFund = rootView.findViewById(R.id.fragment_fund_more_info_month_fund)
        mMonthCDI = rootView.findViewById(R.id.fragment_fund_more_info_month_cdi)
        mYearFund = rootView.findViewById(R.id.fragment_fund_more_info_year_fund)
        mYearCDI = rootView.findViewById(R.id.fragment_fund_more_info_year_cdi)
        m12MonthFund = rootView.findViewById(R.id.fragment_fund_more_info_twelve_months_fund)
        m12MonthCDI = rootView.findViewById(R.id.fragment_fund_more_info_twelve_months_cdi)
        mInfoContainer = rootView.findViewById(R.id.fragment_fund_info_container)
        mInvestButton = rootView.findViewById(R.id.fragment_fund_invest_button)
    }

    private fun loadValues() {
        with(mFundTemplate.screen) {
            mTitle.text = title
            mFundName.text = fundName
            mWhatIs.text = whatIs
            mDefinition.text = definition
            mRiskTitle.text = riskTitle
            loadRiskItem()
            mInfoTitle.text = infoTitle
            moreInfo?.month?.fund?.toString()?.let { mMonthFund.text = getString(R.string.percentage_value, it) }
            moreInfo?.month?.CDI?.toString()?.let { mMonthCDI.text = getString(R.string.percentage_value, it) }
            moreInfo?.year?.fund?.toString()?.let { mYearFund.text = getString(R.string.percentage_value, it) }
            moreInfo?.year?.CDI?.toString()?.let { mYearCDI.text = getString(R.string.percentage_value, it) }
            moreInfo?.twelveMonths?.fund?.toString()?.let { m12MonthFund.text = getString(R.string.percentage_value, it) }
            moreInfo?.twelveMonths?.CDI?.toString()?.let { m12MonthCDI.text = getString(R.string.percentage_value, it) }
            for (item in info) loadInfoItem(item.name, item.data)
            for (item in downInfo) loadDownInfoItem(item.name)
        }
    }

    private fun loadRiskItem() {
        var i = 0
        while (i < mRiskBar.childCount) {

            val item = mRiskBar.getChildAt(i) as RelativeLayout
            val selector: View = item.getChildAt(0)
            val bar: View = item.getChildAt(1)

            if (mFundTemplate.screen.risk == ++i) {
                selector.visibility = View.VISIBLE
                val barParams = bar.layoutParams
                barParams.height = resources.getDimension(R.dimen.risk_bar_selected).toInt()
                (barParams as ViewGroup.MarginLayoutParams).setMargins(0,-6,0,0)
                bar.layoutParams = barParams
            }

            else {
                selector.visibility = View.INVISIBLE
                val barParams = bar.layoutParams
                barParams.height = resources.getDimension(R.dimen.risk_bar_unselected).toInt()
                (barParams as ViewGroup.MarginLayoutParams).setMargins(0,0,0,0)
                bar.layoutParams = barParams
            }

        }
    }

    private fun loadInfoItem(label: String?, value: String?) {
        val item = LayoutInflater.from(mInfoContainer.context).inflate(R.layout.fragment_fund_info_item, mInfoContainer, false)
        val labelTarget: TextView = item.findViewById(R.id.fragment_fund_info_item_label)
        val valueTarget: TextView = item.findViewById(R.id.fragment_fund_info_item_value)
        labelTarget.text = label
        valueTarget.text = value
        mInfoContainer.addView(item)
    }

    private fun loadDownInfoItem(label: String?) {
        val item = LayoutInflater.from(mInfoContainer.context).inflate(R.layout.fragment_fund_down_info_item, mInfoContainer, false)
        val labelTarget: TextView = item.findViewById(R.id.fragment_fund_down_info_item_label)
        labelTarget.text = label
        mInfoContainer.addView(item)
    }

    private fun loadObservers() {
        mViewModel.observe().observe(this, Observer {

            mBodyContainer.visibility = View.GONE
            mProgressContainer.visibility = View.GONE
            mEmptyMessageContainer.visibility = View.GONE

            if (it != null && it.isLoading())
                mProgressContainer.visibility = View.VISIBLE
            else {

                if (it?.data == null || it.data!!.screen.title == null) {
                    mEmptyMessageContainer.visibility = View.VISIBLE
                }

                it?.data?.let { mFundTemplate = it }
                loadValues()
                mBodyContainer.visibility = View.VISIBLE

            }

        })
    }

    companion object {
        fun getInstance(): FundFragment {
            return FundFragment()
        }
    }

}