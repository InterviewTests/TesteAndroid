package com.fuinha11.test.testeandroid.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fuinha11.test.testeandroid.R
import com.fuinha11.test.testeandroid.data.model.Investment
import kotlinx.android.synthetic.main.investment_info_cell.view.*

class MoreInfoAdapter(private val items : List<Investment.Info>) : RecyclerView.Adapter<MoreInfoAdapter.ViewHolder>() {
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.investment_info_cell, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Investment.Info) = with(itemView) {
            with(item){
                title.text = name
                if (data == null) {content.text = "Baixar"
                    download_arrow.visibility = android.view.View.VISIBLE
                    content.setTextColor(android.support.v4.content.ContextCompat.getColor(itemView.context, R.color.colorPrimaryDark))
                } else
                    content.text = data

            }
        }
    }
}