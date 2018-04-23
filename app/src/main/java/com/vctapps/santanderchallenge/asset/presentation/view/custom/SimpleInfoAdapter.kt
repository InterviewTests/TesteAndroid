package com.vctapps.santanderchallenge.asset.presentation.view.custom

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.vctapps.santanderchallenge.R
import com.vctapps.santanderchallenge.asset.presentation.model.Info

class SimpleInfoAdapter(val infoList: MutableList<Info>):
        RecyclerView.Adapter<SimpleInfoAdapter.SimpleInfoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleInfoViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.simple_info_item, parent, false)

        return SimpleInfoViewHolder(view)
    }

    override fun getItemCount() = infoList.size

    override fun onBindViewHolder(holder: SimpleInfoViewHolder, position: Int) {
        holder.setInfos(infoList[position])
    }

    inner class SimpleInfoViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val message1 = view.findViewById(R.id.textViewMessage1) as TextView
        val message2 = view.findViewById(R.id.textViewMessage2) as TextView

        fun setInfos(info: Info){
            message1.text = info.message1
            message2.text = info.message2
        }

    }

}