package com.santander.luizlago.testeandroid.ui.fragments.investment.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.santander.luizlago.testeandroid.R
import com.santander.luizlago.testeandroid.api.models.InfoValue
import kotlinx.android.synthetic.main.layout_investment_down_info.view.*

class InvestmentDownInfoView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    var viewLayout: View?= null
    var infoValue: InfoValue? = null

    init {
        viewLayout = LayoutInflater.from(context)
                .inflate(R.layout.layout_investment_down_info, this, true)
    }

    fun setDownInfoValue(downInfoValue: InfoValue) {
        this.infoValue = downInfoValue
        this.downInfoNameTextView.text = downInfoValue.name
        this.downInfoLayoutButton.setOnClickListener {
            Toast.makeText(this.context!!, R.string.downloadClicked, Toast.LENGTH_SHORT).show()
        }
    }

}