package com.galdino.testandroid.plataform.views.investment

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.galdino.testandroid.R
import com.galdino.testandroid.data.entity.investment.Risk
import kotlinx.android.extensions.LayoutContainer

class RiskAdapter(val mList: List<Risk>): RecyclerView.Adapter<RiskAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val inflate = inflater.inflate(R.layout.adapter_more_info, viewGroup, false)
        return ViewHolder(inflate)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class ViewHolder(override val containerView: View) : LayoutContainer, RecyclerView.ViewHolder(containerView) {

    }
}