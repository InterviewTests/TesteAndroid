package lucasonofre.santandertest.adapter

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.adapter_more_info_item.view.*
import lucasonofre.santandertest.R
import lucasonofre.santandertest.model.Info

class InfoAdapter(private val context: Activity, private val itens: ArrayList<Info>):RecyclerView.Adapter<InfoAdapter.ViewHolderItem>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): InfoAdapter.ViewHolderItem {

        var view:View = LayoutInflater.from(context).inflate(R.layout.adapter_info_item,parent,false)

        return InfoAdapter.ViewHolderItem(view)

    }

    override fun getItemCount(): Int {
        return itens.size
    }

    override fun onBindViewHolder(holder: ViewHolderItem, position: Int) {

        val item = itens[position]
        holder.title.text   = item.name
        holder.data.text    = item.data

    }

    class ViewHolderItem(itemView: View): RecyclerView.ViewHolder(itemView) {

        val title = itemView.info_list_item_title
        val data  = itemView.info_list_item_data
    }


}

