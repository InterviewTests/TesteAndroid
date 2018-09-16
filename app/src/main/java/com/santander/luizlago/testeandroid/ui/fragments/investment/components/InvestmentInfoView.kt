package com.santander.luizlago.testeandroid.ui.fragments.investment.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.santander.luizlago.testeandroid.R
import com.santander.luizlago.testeandroid.api.models.InfoValue
import kotlinx.android.synthetic.main.layout_investment_info.view.*

class InvestmentInfoView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    var viewLayout: View?= null

    init {
        viewLayout = LayoutInflater.from(context)
                .inflate(R.layout.layout_investment_info, this, true)
    }

    fun setInfoValue(infoValue: InfoValue) {
        this.infoNameTextView.text = infoValue.name
        this.downInfoLayoutButton.text = infoValue.data
    }
}