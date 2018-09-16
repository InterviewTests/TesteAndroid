package com.santander.luizlago.testeandroid.ui.fragments.investment.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.santander.luizlago.testeandroid.R
import com.santander.luizlago.testeandroid.api.models.Fund
import kotlinx.android.synthetic.main.layout_investment_risk.view.*

class InvestmentRiskView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    var viewLayout: View?= null

    init {
        viewLayout = LayoutInflater.from(context)
                .inflate(R.layout.layout_investment_risk, this, true)
    }

    fun setFund(fund: Fund) {
        this.riskTitleTextView.text = fund.riskTitle
        when(fund.risk) {
            1 -> adjustIndicator(this.viewRiskLevel01)
            2 -> adjustIndicator(this.viewRiskLevel02)
            3 -> adjustIndicator(this.viewRiskLevel03)
            4 -> adjustIndicator(this.viewRiskLevel04)
            5 -> adjustIndicator(this.viewRiskLevel05)
        }
    }

    private fun adjustIndicator(levelView: View) {
        viewLayout?.post {
            val layoutParams = levelView.layoutParams
            layoutParams.height += 10
            levelView.layoutParams = layoutParams
            this.indicatorView.translationX = levelView.x
        }
    }
}