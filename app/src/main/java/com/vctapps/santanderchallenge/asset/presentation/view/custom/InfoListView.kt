package com.vctapps.santanderchallenge.asset.presentation.view.custom

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.vctapps.santanderchallenge.R
import com.vctapps.santanderchallenge.asset.presentation.model.Info
import kotlinx.android.synthetic.main.info_list_view.view.*

class InfoListView(context: Context, attrs: AttributeSet): LinearLayout(context, attrs) {

    init {
        LayoutInflater.from(context).inflate(R.layout.info_list_view, this)
    }

    fun setSimpleInfos(infoList: MutableList<Info>){
        val adapter = SimpleInfoAdapter(infoList)

        recyclerViewListInfo.adapter = adapter

        recyclerViewListInfo.layoutManager = LinearLayoutManager(context)
    }

    fun setInfosWithLink(infoList: MutableList<Info>){
        val adapter = InfoWithLinkAdapter(infoList)

        recyclerViewListInfo.adapter = adapter

        recyclerViewListInfo.layoutManager = LinearLayoutManager(context)
    }

}