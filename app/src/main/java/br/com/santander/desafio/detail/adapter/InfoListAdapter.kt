package br.com.santander.desafio.detail.adapter

import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.com.santander.desafio.R
import br.com.santander.desafio.webservice.fund.InfoItem
import kotlinx.android.synthetic.main.item_info.view.*

/**
 * created by enzoteles on 25/Julho/2018
 * email enzo.carvalho.teles@gmail.com
 * Android Developer Software Sr.
 */

class InfoListAdapter(val listInfo: List<InfoItem?>?, val context: Context) : Adapter<InfoListAdapter.ViewHoder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHoder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_info, parent, false)
        return ViewHoder(view)
    }

    override fun getItemCount(): Int {
        return listInfo!!.size
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onBindViewHolder(holder: ViewHoder, position: Int) {
        val info = listInfo!![position]

        holder?.let {

            it.name.setText("${info!!.name}")
            it.date.setText("${info!!.data}")
        }
    }

    class ViewHoder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name = itemView.dt_tv_info_name
        var date = itemView.dt_tv_info_data
    }
}

