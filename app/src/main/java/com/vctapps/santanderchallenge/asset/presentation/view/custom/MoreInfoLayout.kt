package com.vctapps.santanderchallenge.asset.presentation.view.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.vctapps.santanderchallenge.R
import com.vctapps.santanderchallenge.asset.presentation.model.Info
import kotlinx.android.synthetic.main.more_info_layout.view.*

class MoreInfoLayout(context: Context, attrs: AttributeSet): LinearLayout(context, attrs) {

    init {
        LayoutInflater.from(context).inflate(R.layout.more_info_layout, this)
    }

    fun setMoreInfos(title: String, onMonth: Info, onYear: Info, twelveMonths: Info){
        textViewMoreInfoTitle.text = title

        textViewMoreInfoFundMonth.text = onMonth.message1
        textViewMoreInfoCdiMonth.text = onMonth.message2

        textViewMoreInfoFundYear.text = onYear.message1
        textViewMoreInfoCdiYear.text = onYear.message2

        textViewMoreInfoFundTwelve.text = twelveMonths.message1
        textViewMoreInfoCdiTwelve.text = twelveMonths.message2
    }

}