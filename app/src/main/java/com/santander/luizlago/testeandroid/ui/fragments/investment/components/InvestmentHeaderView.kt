package com.santander.luizlago.testeandroid.ui.fragments.investment.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.santander.luizlago.testeandroid.R

class InvestmentHeaderView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    var viewLayout: View?= null

    init {
        viewLayout = LayoutInflater.from(context)
                .inflate(R.layout.layout_investment_header, this, true)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        val marginLayoutParams = ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        this.layoutParams = marginLayoutParams
//        viewLayout = View.inflate(this.context, R.layout.layout_investment_header, this)
    }

}