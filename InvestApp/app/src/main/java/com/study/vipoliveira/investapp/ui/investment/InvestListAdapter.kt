package com.study.vipoliveira.investapp.ui.investment

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.study.vipoliveira.investapp.R
import com.study.vipoliveira.investapp.data.network.investment.entities.Info
import kotlinx.android.synthetic.main.layout_ivestment_item.view.*

class InvestListAdapter (val items : List<Info>) : RecyclerView.Adapter<InvestListAdapter.ViewHolder>() {
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_ivestment_item, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Info) = with(itemView) {
            with(item){
                title.text = name
                if (data != null) {
                    content.text = data
                } else {
                    content.text = context.getString(R.string.download_content)
                    imgDownload.visibility = View.VISIBLE
                    content.setTextColor(ContextCompat.getColor(itemView.context, R.color.colorPrimary))

                }
            }
        }
    }
}