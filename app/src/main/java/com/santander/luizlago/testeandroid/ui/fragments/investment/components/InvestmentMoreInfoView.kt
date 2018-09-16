package com.santander.luizlago.testeandroid.ui.fragments.investment.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.santander.luizlago.testeandroid.R
import com.santander.luizlago.testeandroid.api.models.Fund
import kotlinx.android.synthetic.main.layout_investment_more_info.view.*

class InvestmentMoreInfoView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    var viewLayout: View?= null

    init {
        viewLayout = LayoutInflater.from(context)
                .inflate(R.layout.layout_investment_more_info, this, true)
    }

    fun setFund(fund: Fund) {
        this.titleTextView.text = fund.infoTitle
        this.monthFundTextView.text = fund.moreInfo.month.fund.toString() + "%"
        this.monthCdiTextView.text = fund.moreInfo.month.CDI.toString() + "%"
        this.yearFundTextView.text = fund.moreInfo.year.fund.toString() + "%"
        this.yearCdiTextView.text = fund.moreInfo.year.CDI.toString() + "%"
        this.twelveMonthsFundTextView.text = fund.moreInfo.twelveMonths.fund.toString() + "%"
        this.twelveMonthsCdiTextView.text = fund.moreInfo.twelveMonths.CDI.toString() + "%"
    }

}