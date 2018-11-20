package com.galdino.testandroid.plataform.views.investment

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.galdino.testandroid.R
import com.galdino.testandroid.data.entity.investment.Risk
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.adapter_risk.*

class RiskAdapter(private val mList: List<Risk>): RecyclerView.Adapter<RiskAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val inflate = inflater.inflate(R.layout.adapter_risk, viewGroup, false)
        return ViewHolder(inflate)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val risk = mList[position]
        val context = holder.vRiskSelected.context
        val color = ContextCompat.getColor(context, risk.colorId)
        if(risk.selected) {
            holder.vRiskSelected.setBackgroundColor(color)
            holder.vRiskSelected.visibility = View.VISIBLE
            holder.vRisk.visibility = View.INVISIBLE
            holder.ivArrowDown.visibility = View.VISIBLE
        }
        else{
            holder.vRisk.setBackgroundColor(color)
            holder.vRisk.visibility = View.VISIBLE
            holder.vRiskSelected.visibility = View.INVISIBLE
            holder.ivArrowDown.visibility = View.INVISIBLE
        }
    }

    class ViewHolder(override val containerView: View) : LayoutContainer, RecyclerView.ViewHolder(containerView) {

    }
}