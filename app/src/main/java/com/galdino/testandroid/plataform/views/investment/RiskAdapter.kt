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

class RiskAdapter(private val mList: List<Risk>, private val mWidthScreen: Int): RecyclerView.Adapter<RiskAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val inflate = inflater.inflate(R.layout.adapter_risk, viewGroup, false)
        return ViewHolder(inflate)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.clRisk.layoutParams.width = mWidthScreen

        val risk = mList[position]
        val context = holder.vRiskSelected.context
        val color = ContextCompat.getColor(context, risk.colorId)
        if(position == mList.size-1) {
            holder.vMargin.visibility = View.GONE
            holder.vBackgroundMargin.visibility = View.GONE
        }
        holder.vBackgroundMargin.setBackgroundColor(color)
        if(risk.selected) {
            setRiskInformation(holder.vRiskSelected,holder.vRisk ,risk, color)
            holder.ivArrowDown.visibility = View.VISIBLE
        }
        else{
            setRiskInformation(holder.vRisk ,holder.vRiskSelected,risk, color)
            holder.ivArrowDown.visibility = View.INVISIBLE
        }
    }

    private fun setRiskInformation(vRiskVisible: View, vRiskInvisible: View, risk: Risk, color: Int) {
        when(risk.riskId) {
            1->{
                vRiskVisible.setBackgroundResource(R.drawable.background_rounded_left)
            }
            5->{
                vRiskVisible.setBackgroundResource(R.drawable.background_rounded_right)
            }
            else->{
                vRiskVisible.setBackgroundColor(color)
            }
        }

        vRiskVisible.visibility = View.VISIBLE
        vRiskInvisible.visibility = View.INVISIBLE

    }

    class ViewHolder(override val containerView: View) : LayoutContainer, RecyclerView.ViewHolder(containerView) {

    }
}