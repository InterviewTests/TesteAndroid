package com.vctapps.santanderchallenge.asset.presentation.view.custom

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.vctapps.santanderchallenge.R
import com.vctapps.santanderchallenge.asset.presentation.model.Info
import com.vctapps.santanderchallenge.core.domain.InvalidData

class InfoWithLinkAdapter(val infoList: MutableList<Info>):
        RecyclerView.Adapter<InfoWithLinkAdapter.InfoWithLinkViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoWithLinkViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.info_with_link_item, parent, false)

        return InfoWithLinkViewHolder(view)
    }

    override fun getItemCount() = infoList.size

    override fun onBindViewHolder(holder: InfoWithLinkViewHolder, position: Int) {
        holder.setInfos(infoList[position])
    }

    inner class InfoWithLinkViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val message1 = view.findViewById(R.id.textViewMessage1) as TextView
        val message2 = view.findViewById(R.id.textViewMessage2) as TextView

        fun setInfos(info: Info){
            message1.text = info.message1

            message2.setOnClickListener {
                if(info.message2 == InvalidData.INVALID_STRING){
                    Toast.makeText(itemView.context, "Indisponivel no momento", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(itemView.context, "Seu download começa logo", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

}