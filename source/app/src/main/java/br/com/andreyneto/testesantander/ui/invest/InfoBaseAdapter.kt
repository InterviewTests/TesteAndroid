package br.com.andreyneto.testesantander.ui.invest

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.andreyneto.testesantander.R
import br.com.andreyneto.testesantander.model.Info
import kotlinx.android.synthetic.main.list_more_info.view.*

class InfoBaseAdapter(private val infos: List<Info>,
                      private val context: Context) : RecyclerView.Adapter<InfoBaseAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_more_info, p0, false))
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val info = infos[p1]
        p0.bindView(info)
    }

    override fun getItemCount(): Int {
        return infos.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindView(info: Info) {
            itemView.more_info_data.text = info.data
            itemView.more_info_fund.text = info.fund
            itemView.more_info_title.text = info.name
        }
    }
}