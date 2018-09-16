package com.santander.luizlago.testeandroid.ui.fragments.investment.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.santander.luizlago.testeandroid.R
import com.santander.luizlago.testeandroid.api.models.Fund
import kotlinx.android.synthetic.main.layout_investment_header.view.*

class InvestmentHeaderView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    var viewLayout: View?= null
    var listener: InvestmentHeaderViewListener? = null

    init {
        viewLayout = LayoutInflater.from(context)
                .inflate(R.layout.layout_investment_header, this, true)
    }

    fun setFund(fund: Fund) {
        this.titleText.text = fund.title
        this.fundNameText.text = fund.fundName
        this.whatIsText.text = fund.whatIs
        this.definitionText.text = fund.definition
        this.shareButton.setOnClickListener {
            this.listener?.onShareButtonClicked()
        }
    }

    interface InvestmentHeaderViewListener {
        fun onShareButtonClicked()
    }
}