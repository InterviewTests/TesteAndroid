package com.galdino.testandroid.plataform.views.contact

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.galdino.testandroid.R
import com.galdino.testandroid.domain.model.Cell
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.adapter_form_text_view.*

class FormAdapter(val mList: List<Cell>): RecyclerView.Adapter<FormAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val inflater= LayoutInflater.from(viewGroup.context)
        val view = when(viewType) {
            Cell.Type.FIELD->{
                inflater.inflate(R.layout.adapter_form_edit_text, viewGroup, false)
            }
            Cell.Type.TEXT->{
                inflater.inflate(R.layout.adapter_form_text_view, viewGroup, false)
            }
            Cell.Type.CHECK_BOX->{
                inflater.inflate(R.layout.adapter_form_checkbox, viewGroup, false)
            }
            else-> {
                inflater.inflate(R.layout.adapter_form_text_view, viewGroup, false)
            }

        }

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        when(getItemViewType(position))
        {
            Cell.Type.TEXT->{
                viewHolder.tvCell.text = mList[position].message
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return mList[position].type
    }

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer{

    }
}