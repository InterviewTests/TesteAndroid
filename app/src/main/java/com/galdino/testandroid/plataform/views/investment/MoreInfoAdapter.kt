package com.galdino.testandroid.plataform.views.investment

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.galdino.testandroid.R
import com.galdino.testandroid.data.entity.investment.PeriodModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.adapter_more_info.*

class MoreInfoAdapter(private val mList: List<PeriodModel>): RecyclerView.Adapter<MoreInfoAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val inflate = inflater.inflate(R.layout.adapter_more_info, viewGroup, false)
        return ViewHolder(inflate)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val moreInfo = mList[position]
        val context = holder.tvFund.context
        holder.tvLabel.text = moreInfo.title
        holder.tvFund.text = context.getString(R.string.percent,moreInfo.fund?.toString())
        holder.tvCdi.text = context.getString(R.string.percent,moreInfo.cdi?.toString())
    }

    class ViewHolder(override val containerView: View): LayoutContainer, RecyclerView.ViewHolder(containerView) {

    }
}