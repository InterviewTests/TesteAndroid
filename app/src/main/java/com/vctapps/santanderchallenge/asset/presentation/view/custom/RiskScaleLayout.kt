package com.vctapps.santanderchallenge.asset.presentation.view.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.vctapps.santanderchallenge.R
import com.vctapps.santanderchallenge.asset.presentation.model.RiskInfo
import kotlinx.android.synthetic.main.risk_scale.view.*

class RiskScaleLayout(context: Context, attrs: AttributeSet): LinearLayout(context, attrs) {

    init{
        LayoutInflater.from(context).inflate(R.layout.risk_scale, this)
    }

    fun setRiskInfo(riskInfo: RiskInfo){
        textViewRiskTitle.text = riskInfo.title

        when(riskInfo.risk){
            0 -> setRiskIndicator(colorIndicatorLower, iconIndicatorLower)
            1 -> setRiskIndicator(colorIndicatorLow, iconIndicatorLow)
            2 -> setRiskIndicator(colorIndicatorMedium, iconIndicatorMedium)
            3 -> setRiskIndicator(colorIndicatorHigh, iconIndicatorHigh)
            4 -> setRiskIndicator(colorIndicatorHigher, iconIndicatorHigher)
        }
    }

    fun setRiskIndicator(colorIndicator: View, iconIndicator: ImageView){
        val params = colorIndicator.layoutParams
        params.height = 35
        colorIndicator.layoutParams = params

        iconIndicator.setImageResource(R.drawable.ic_arrow_down_gray_24dp)
    }

}