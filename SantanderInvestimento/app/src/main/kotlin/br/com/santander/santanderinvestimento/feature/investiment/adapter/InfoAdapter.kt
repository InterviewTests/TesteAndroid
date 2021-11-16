package br.com.santander.santanderinvestimento.feature.investiment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.santander.santanderinvestimento.R
import br.com.santander.santanderinvestimento.feature.investiment.domain.entity.Info
import kotlinx.android.synthetic.main.item_view_info.view.*

class InfoAdapter(listItems: MutableList<Info>) : RecyclerView.Adapter<InfoAdapter.InfoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view_info, parent, false)
        return InfoViewHolder(view)
    }

    private val infoListItems: MutableList<Info> = listItems


    override fun getItemCount() = infoListItems.size

    override fun onBindViewHolder(holder: InfoViewHolder, position: Int) {
        val note = infoListItems[position]
        holder.bindView(note)
    }

    inner class InfoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindView(item: Info) {
            setIsRecyclable(false)
            itemView.txName.text = item.name
            itemView.txTax.text = item.data
        }
    }
}