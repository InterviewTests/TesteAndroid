package com.vctapps.santanderchallenge.asset.presentation.view.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.vctapps.santanderchallenge.R
import com.vctapps.santanderchallenge.asset.presentation.model.Info
import com.vctapps.santanderchallenge.asset.presentation.model.MoreInfos
import kotlinx.android.synthetic.main.more_info_layout.view.*

class MoreInfoLayout(context: Context, attrs: AttributeSet): LinearLayout(context, attrs) {

    init {
        LayoutInflater.from(context).inflate(R.layout.more_info_layout, this)
    }

    fun setMoreInfos(moreInfos: MoreInfos){
        textViewMoreInfoTitle.text = moreInfos.title

        textViewMoreInfoFundMonth.text = moreInfos.onMonth.message1
        textViewMoreInfoCdiMonth.text = moreInfos.onMonth.message2

        textViewMoreInfoFundYear.text = moreInfos.onYear.message1
        textViewMoreInfoCdiYear.text = moreInfos.onYear.message2

        textViewMoreInfoFundTwelve.text = moreInfos.onTwelveMonths.message1
        textViewMoreInfoCdiTwelve.text = moreInfos.onTwelveMonths.message2
    }

}