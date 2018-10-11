package br.com.santander.santanderinvestimento.investiment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.santander.santanderinvestimento.R
import br.com.santander.santanderinvestimento.investiment.domain.entity.Info
import kotlinx.android.synthetic.main.item_view_down_info.view.*

class DownInfoAdapter(listItems: MutableList<Info>, val clickListener: (String) -> Unit) : RecyclerView.Adapter<DownInfoAdapter.DownInfoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DownInfoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view_down_info, parent, false)
        return DownInfoViewHolder(view)
    }

    private val infoListItems: MutableList<Info> = listItems


    override fun getItemCount() = infoListItems.size

    override fun onBindViewHolder(holder: DownInfoViewHolder, position: Int) {
        val note = infoListItems[position]
        holder.bindView(note)
    }

    inner class DownInfoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindView(item: Info) {
            setIsRecyclable(false)
            itemView.txName.text = item.name
            itemView.clDownload.setOnClickListener { clickListener("") }
        }
    }
}