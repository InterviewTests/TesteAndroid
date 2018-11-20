package com.galdino.testandroid.plataform.views.investment

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.galdino.testandroid.R
import com.galdino.testandroid.data.entity.investment.Info
import com.galdino.testandroid.data.entity.investment.InfoModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.adapter_info.*

class InfoAdapter(private val mList: List<InfoModel>): RecyclerView.Adapter<InfoAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val inflate = inflater.inflate(R.layout.adapter_info, viewGroup, false)
        return ViewHolder(inflate)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val infoModel = mList[position]
        holder.tvName.text = infoModel.name
        holder.tvData.text = infoModel.data
    }

    class ViewHolder(override val containerView: View) : LayoutContainer, RecyclerView.ViewHolder(containerView) {

    }
}