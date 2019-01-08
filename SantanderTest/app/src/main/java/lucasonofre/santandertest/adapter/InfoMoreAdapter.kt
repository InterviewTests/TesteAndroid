package lucasonofre.santandertest.adapter

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.adapter_more_info_item.view.*
import lucasonofre.santandertest.R
import lucasonofre.santandertest.model.YieldListItem

class InfoMoreAdapter(private val context: Activity, private val itens: ArrayList<YieldListItem>):RecyclerView.Adapter<InfoMoreAdapter.ViewHolderItem>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): InfoMoreAdapter.ViewHolderItem {

        var view:View = LayoutInflater.from(context).inflate(R.layout.adapter_more_info_item,parent,false)

        return InfoMoreAdapter.ViewHolderItem(view)

    }

    override fun getItemCount(): Int {
        return itens.size
    }

    override fun onBindViewHolder(holder: ViewHolderItem, position: Int) {

        val item  = itens[position]

        holder.dateItem.text   = item.titleItem
        holder.fund.text       = item.yield?.fund.toString() + "%"
        holder.cdi.text        = item.yield?.cdi.toString() + "%"

    }

    class ViewHolderItem(itemView: View): RecyclerView.ViewHolder(itemView) {

        val dateItem = itemView.info_list_item_title
        val fund     = itemView.moreList_fund_item
        val cdi      = itemView.info_list_item_data
    }


}

