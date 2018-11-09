package br.com.gservices.santanderteste.pages.investments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.gservices.santanderteste.R
import br.com.gservices.santanderteste.pages.investments.data.entities.Informations
import kotlinx.android.synthetic.main.item_down_info.view.*

class DownInformationsAdapter(listItems: MutableList<Informations>, val clickListener: (String) -> Unit) :
    RecyclerView.Adapter<DownInformationsAdapter.DownInfoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DownInfoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_down_info, parent, false)
        return DownInfoViewHolder(view)
    }

    private val infoListItems: MutableList<Informations> = listItems


    override fun getItemCount() = infoListItems.size

    override fun onBindViewHolder(holder: DownInfoViewHolder, position: Int) {
        val note = infoListItems[position]
        holder.bindView(note)
    }

    inner class DownInfoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindView(item: Informations) {
            setIsRecyclable(false)
            itemView.txName.text = item.name
            itemView.clDownload.setOnClickListener { clickListener("") }
        }
    }
}