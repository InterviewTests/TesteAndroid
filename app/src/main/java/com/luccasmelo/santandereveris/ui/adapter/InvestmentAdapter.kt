package com.luccasmelo.santandereveris.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.luccasmelo.santandereveris.BR

class InvestmentInformationAdapters(val context: Context, val layout: Int, val list: ArrayList<Any>) :
    RecyclerView.Adapter<GenericViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {
        return GenericViewHolder(DataBindingUtil.inflate(LayoutInflater.from(context), layout, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        holder.bind(list[position], BR.info)
    }
}


class GenericViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(any: Any, identification: Int) {
        binding.setVariable(BR.info, any)
        binding.executePendingBindings()
        binding.invalidateAll()

    }
}