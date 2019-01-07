package lucasonofre.santandertest.adapter

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.adapter_more_info_item.view.*
import lucasonofre.santandertest.R
import lucasonofre.santandertest.custom.InfoDownButton
import lucasonofre.santandertest.model.DownInfo
import lucasonofre.santandertest.model.Info

class InfoDownAdapter(private val context: Activity, private val itens: ArrayList<DownInfo>):RecyclerView.Adapter<InfoDownAdapter.ViewHolderItem>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): InfoDownAdapter.ViewHolderItem {

        var view:View = LayoutInflater.from(context).inflate(R.layout.adapter_info_down_item,parent,false)

        return InfoDownAdapter.ViewHolderItem(view)

    }

    override fun getItemCount(): Int {
        return itens.size
    }

    override fun onBindViewHolder(holder: ViewHolderItem, position: Int) {

        val item = itens[position]
        holder.title.text   = item.name

        if(item.data != null)
            holder.data.text    = item.data.toString()

    }

    class ViewHolderItem(itemView: View): RecyclerView.ViewHolder(itemView) {

        val title = itemView.info_list_item_title
        val data  = itemView.info_list_item_data
    }


}

