package com.anabhomasi.androidapp.views.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anabhomasi.androidapp.App
import com.anabhomasi.androidapp.R
import kotlinx.android.synthetic.main.layout_info_row.view.*

class FormAdapter: RecyclerView.Adapter<FormViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FormViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellFromRow = layoutInflater.inflate(R.layout.layout_info_row, parent, false)
        return FormViewHolder(cellFromRow)
    }

    override fun getItemCount(): Int {
        return App.getInstance().form.cells.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: FormViewHolder, position: Int) {
        holder.view.apply {

        }
    }
}

class FormViewHolder(val view: View): RecyclerView.ViewHolder(view){

}