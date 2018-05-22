package com.anabhomasi.androidapp.views.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anabhomasi.androidapp.App
import com.anabhomasi.androidapp.R
import com.anabhomasi.androidapp.data.models.Fund
import kotlinx.android.synthetic.main.layout_info_row.view.*
import kotlinx.android.synthetic.main.layout_moreinfo_row.view.*

class InfoAdapter: RecyclerView.Adapter<InfoViewHolder>() {

    private var infoList = listOf<Fund.Info>()

    init {
        infoList = App.getInstance().funds.screen.info.union(App.getInstance().funds.screen.downInfo).toList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellFromRow = layoutInflater.inflate(R.layout.layout_info_row, parent, false)
        return InfoViewHolder(cellFromRow)
    }

    override fun getItemCount(): Int {
        return infoList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: InfoViewHolder, position: Int) {
        holder.view.apply {
            nameInfoTxV.text = infoList[position].name

            if (infoList[position].data.isNullOrBlank()) {

                dataInfoBtn.setTextColor(Color.RED)
                dataInfoBtn.text = "Baixar"
                dataInfoBtn.isClickable = true
                dataInfoBtn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_launcher_foreground, 0, 0, 0);
            }
            else{
                dataInfoBtn.text = infoList[position].data
                dataInfoBtn.setTextColor(Color.BLACK)
                dataInfoBtn.isClickable = false
                dataInfoBtn.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

            }
        }
    }
}

class InfoViewHolder(val view: View): RecyclerView.ViewHolder(view){

}