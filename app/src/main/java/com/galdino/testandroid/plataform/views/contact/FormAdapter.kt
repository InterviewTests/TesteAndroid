package com.galdino.testandroid.plataform.views.contact

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.galdino.testandroid.R
import com.galdino.testandroid.domain.model.Cell
import com.galdino.testandroid.domain.model.CellAnswer
import com.galdino.testandroid.util.MaskUtils
import com.galdino.testandroid.util.MasksType
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.adapter_form_checkbox.*
import kotlinx.android.synthetic.main.adapter_form_edit_text.*
import kotlinx.android.synthetic.main.adapter_form_send.*
import kotlinx.android.synthetic.main.adapter_form_text_view.*


class FormAdapter(private val mList: List<Cell>, private val mListener: FormAdapter.Listener): RecyclerView.Adapter<FormAdapter.ViewHolder>() {
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
            Cell.Type.SEND->{
                inflater.inflate(R.layout.adapter_form_send, viewGroup, false)
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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cell = mList[position]
        when(getItemViewType(position))
        {
            Cell.Type.FIELD->{
                holder.tilCell.hint = cell.message
                cell.field = holder.etCell
                setCommonData(holder.tilCell, holder.clRootEditText, cell, false)
                val etCell = holder.etCell
                when(cell.typefield)
                {
                    Cell.TypeField.TELL_NUMBER_S,
                    Cell.TypeField.TELL_NUMBER ->
                    {
                        MaskUtils.initializePhoneMask(MasksType.PHONE_NUMBER, etCell,cell)
                        etCell.inputType = InputType.TYPE_CLASS_NUMBER
                    }
                    else->{
                        MaskUtils.initializeTextWatcher(etCell,cell)
                        etCell.inputType = InputType.TYPE_CLASS_TEXT
                    }
                }
                holder.ivClose.setOnClickListener {
                    holder.etCell.setText("")
                }
            }
            Cell.Type.TEXT->{
                holder.tvCell.text = cell.message
                setCommonData(holder.tvCell, holder.clRootTextView, cell, false)
            }
            Cell.Type.CHECK_BOX->{
                holder.cbCell.text = cell.message
                setCommonData(holder.cbCell, holder.clRootCheckbox, cell, false)
                holder.cbCell.setOnCheckedChangeListener { _, p1 ->
                    cell.cellAnswer = CellAnswer(boolean = p1)
                }
            }
            Cell.Type.SEND->{
                holder.btSend.text = cell.message
                setCommonData(holder.btSend,holder.clRootSend, cell, true)
                holder.btSend.setOnClickListener {
                    mListener.onSendClicked(mList)
                }
            }
        }
    }

    private fun setCommonData(viewToMarginUp: View, clRootToVisibility: ConstraintLayout, cell: Cell, isButton: Boolean) {
        if(cell.hidden != null && cell.hidden) {
            clRootToVisibility.visibility = View.GONE
            clRootToVisibility.layoutParams = RecyclerView.LayoutParams(0, 0)
        }
        else{
            setMarginTop(viewToMarginUp,cell,isButton)
        }
    }

    private fun setMarginTop(viewToMarginUp: View, cell: Cell, isButton: Boolean) {
        cell.topSpacing?.let {
            val layoutParams = viewToMarginUp.layoutParams as ConstraintLayout.LayoutParams
            val resources = viewToMarginUp.context.resources
            val density = resources.displayMetrics.density
            val px = it * density
            var bottomMargin = 0
            if(isButton)
            {
               bottomMargin = resources.getDimension(R.dimen.margin_default4x).toInt()
            }
            layoutParams.setMargins(0, px.toInt(), 0, bottomMargin) // left, top, right, bottom
            viewToMarginUp.layoutParams = layoutParams
        }
    }

    override fun getItemViewType(position: Int): Int {
        return mList[position].type
    }

    inner class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer{

    }

    interface Listener {
        fun onSendClicked(cells: List<Cell>)
    }
}