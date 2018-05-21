package com.anabhomasi.androidapp.views.adapters

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anabhomasi.androidapp.App
import com.anabhomasi.androidapp.R
import com.anabhomasi.androidapp.data.models.Fund
import kotlinx.android.synthetic.main.layout_moreinfo_row.view.*

class MoreInfoAdapter: RecyclerView.Adapter<MoreInfoViewHolder>() {

    private val moreInfoList = arrayListOf<Fund.Revenue>()
    private val moreInfoTitleList = listOf("No mês", "No ano", "12 meses")

    init {
        with(App.getInstance().funds.screen.moreInfo){
            moreInfoList.add(Fund.Revenue(this.month.fund, this.month.CDI))
            moreInfoList.add(Fund.Revenue(this.year.fund, this.year.CDI))
            moreInfoList.add(Fund.Revenue(this.twelveMonths.fund, this.twelveMonths.CDI))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoreInfoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellFromRow = layoutInflater.inflate(R.layout.layout_moreinfo_row, parent, false)
        return MoreInfoViewHolder(cellFromRow)
    }

    override fun getItemCount(): Int {
        return moreInfoList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MoreInfoViewHolder, position: Int) {
        holder.view.apply {
            titleRowTxv.text = moreInfoTitleList[position]
            fundValueRowTxv.text = moreInfoList[position].fund.toString() + "%"
            cdiValueRowTxv.text = moreInfoList[position].CDI.toString() + "%"
        }
    }
}

class MoreInfoViewHolder(val view: View): RecyclerView.ViewHolder(view){

}