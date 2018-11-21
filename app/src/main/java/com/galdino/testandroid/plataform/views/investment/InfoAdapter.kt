package com.galdino.testandroid.plataform.views.investment

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.galdino.testandroid.R
import com.galdino.testandroid.data.entity.investment.DownInfo
import com.galdino.testandroid.data.entity.investment.InfoModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.adapter_down_info.*
import kotlinx.android.synthetic.main.adapter_info.*

class InfoAdapter(private val mList: List<InfoModel>,
                  private val isDown: Boolean = false,
                  private val mListener: InfoAdapter.Listener? = null): RecyclerView.Adapter<InfoAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val inflate = if(isDown){
            inflater.inflate(R.layout.adapter_down_info, viewGroup, false)
        }
        else{
            inflater.inflate(R.layout.adapter_info, viewGroup, false)
        }

        return ViewHolder(inflate)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    interface Listener{
        fun onDownloadClicked(downInfo: DownInfo)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val infoModel = mList[position]
        if(isDown)
        {
            holder.tvNameDown.text = infoModel.name
            holder.vDownload.setOnClickListener {
                mListener?.onDownloadClicked(mList[position] as DownInfo)
            }
        }
        else{
            holder.tvName.text = infoModel.name
            holder.tvData.text = infoModel.data
        }
    }

    class ViewHolder(override val containerView: View) : LayoutContainer, RecyclerView.ViewHolder(containerView) {

    }
}