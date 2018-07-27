package com.rafhack.testeandroid.investment

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.rafhack.testeandroid.R
import com.rafhack.testeandroid.data.entities.investment.Info

class InvestmentInfoAdapter(private val infoList: ArrayList<Info>) : RecyclerView.Adapter<InvestmentInfoAdapter.ViewHolder>() {

    companion object {
        const val typeInfo = 0
        const val typeDownload = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(when (viewType) {
            typeInfo -> R.layout.investment_info_item
            typeDownload -> R.layout.investment_down_info_item
            else -> 0
        }, parent, false))
    }

    override fun getItemViewType(position: Int): Int {
        return if (infoList[position].data == null) typeDownload else typeInfo
    }

    override fun getItemCount(): Int = infoList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(position: Int) {

            val tvwName = itemView.findViewById<TextView>(when (itemViewType) {
                typeInfo -> R.id.investment_info_tvw_name
                typeDownload -> R.id.investment_down_info_tvw_name
                else -> 0
            })

            val tvwData = itemView.findViewById<TextView>(when (itemViewType) {
                typeInfo -> R.id.investment_info_tvw_data
                typeDownload -> R.id.investment_down_info_tvw_download
                else -> 0
            })

            val info = infoList[position]
            tvwName.text = info.name
            if (itemViewType == typeInfo) tvwData.text = info.data
        }
    }

}